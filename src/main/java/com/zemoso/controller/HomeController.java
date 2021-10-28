package com.zemoso.controller;

import com.zemoso.entity.Product;
import com.zemoso.entity.User;
import com.zemoso.helper.Helper;
import com.zemoso.service.OrderedService;
import com.zemoso.service.ProductService;
import com.zemoso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/shoplite")
public class HomeController {
    private UserService userService;
    private ProductService productService;
    private OrderedService orderedService;
    @Autowired
    public HomeController(UserService userService,ProductService productService,OrderedService orderedService){
        this.userService=userService;
        this.productService=productService;
        this.orderedService=orderedService;
    }
    @GetMapping("")
    public String showHomePage(){
        return "home";
    }
    @GetMapping("/admin")
    public String viewHomePage(Model model) {
        return showAdminPage(1, model);
    }
    @GetMapping("/admin/users/{pageNo}")
    public String showAdminPage(@PathVariable (value = "pageNo") int pageNo, Model model)
    {
        int pageSize=2;
        Page<User> page=userService.findPaginated(pageNo,pageSize);
        List<User> users=page.getContent();
        model.addAttribute("users",users);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("currentPage",pageNo);
        return "adminPage";
    }
    @GetMapping("/signup-form")
    public String showSignUpForm(){
        return "signupForm";
    }
    @GetMapping("/signin-form")
    public String showSignInForm(){
        return "signinForm";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/shoplite/signin-form";
    }
   @PostMapping("/user")
    public String showUserPage(@RequestParam(name = "email")String email,@RequestParam(name = "password")String password, Model model){
        List<User> users=userService.findAll();
       for (User user:users) {
           if(user.getEmail().equals(email)&&user.getPassword().equals(password)) {
               model.addAttribute("user",user);
               model.addAttribute("products",productService.findByStatus());
                return "userPage";
           }
       }
       return "invalidPage";
    }
    @GetMapping("/user-update-form")
    public String showFormForUpdate(@RequestParam("userId") int theId,Model model) {
        User user = userService.findById(theId);
        model.addAttribute("user",user);
        return "userForm";
    }
    @PostMapping("/updated-user")
    public String saveUserWithUpdate(@ModelAttribute("user") User user,Model theModel) {
        user.setPassword(userService.findPasswordById(user.getId()));
        userService.save(user);
        theModel.addAttribute("user", user);
        theModel.addAttribute("products",productService.findByStatus());
        return "userPage";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        User user=userService.findById(theId);
        userService.deleteById(theId);
        return "redirect:/shoplite";
    }
    @GetMapping("/product-form")
    public String showProductForm(@RequestParam("id") int id,Model model){
        model.addAttribute("id",id);
        return "productForm";
    }
    @PostMapping("/user/{Id}")
    public String addProduct(@PathVariable("Id") int id,@ModelAttribute("product") Product product,Model model){
        User user=userService.findById(id);
        product.setUser(user);
        user.add(product);
        product.setAvailable(true);
        productService.save(product);
        model.addAttribute("user",user);
        model.addAttribute("products",productService.findByStatus());
        return "userPage";
    }
    @GetMapping("/product-update-form")
    public String showFormForProductUpdate(@RequestParam("id") int uid,@RequestParam("pId") int id,Model model){
        if(!productService.findProductsIdByUserId(uid).contains(id))
            return "accessDenied";
        Optional<Product> product=productService.findById(id);
        model.addAttribute("userid",uid);
        model.addAttribute("product",product);
        return "productUpdateForm";
    }
    @PostMapping("/user/product")
    public String saveProductWithUpdate(@RequestParam("userid")String userid,@ModelAttribute("product") Product product,Model model) {
        User user=userService.findById(Integer.parseInt(userid));
        product.setUser(user);
        product.setAvailable(true);
        productService.save(product);
        model.addAttribute("user",user);
        model.addAttribute("products",productService.findByStatus());
        return "userPage";
    }
    @GetMapping("/user/product")
    public String deleteProduct(@RequestParam("id")String userid,@RequestParam("pId") int theId,Model model) {
        model.addAttribute("user",userService.findById(Integer.parseInt(userid)));
        productService.deleteById(theId);
        model.addAttribute("products",productService.findByStatus());
        return "userPage";
    }
    @GetMapping("/buy-product")
    public String buyProduct(@RequestParam("id")String userid,@RequestParam("pId") int theId,Model model)
    {
        new Helper(orderedService).saveToOrdered(theId,Integer.parseInt(userid));
        model.addAttribute("userid", Integer.parseInt(userid));
        model.addAttribute("user",userService.findById(productService.findUserIdById(theId)));
        productService.updateStatus(theId);
        return "receipt";
    }
    @GetMapping("/user")
    public String showUserPage(@RequestParam("uid") String uid,Model model){
        model.addAttribute("user",userService.findById(Integer.parseInt(uid)));
        model.addAttribute("products",productService.findByStatus());
        return "userPage";
    }
    @GetMapping("/user/products")
    public String showOrderedProducts(@RequestParam("id") String uid,Model model)
    {
        int userid=Integer.parseInt(uid);
        model.addAttribute("userid", userid);
        List<Product> products=new ArrayList<>();
        List<Integer> productIdList=orderedService.findProductsByUserId(userid);
        for (int i: productIdList) {
            products.add(productService.findProductsByProductsId(i));
        }
        model.addAttribute("orderedProducts",products);
        return "userProductsPage";
    }
    @GetMapping("/logout")
    public String showHome(){
        return "home";
    }
}