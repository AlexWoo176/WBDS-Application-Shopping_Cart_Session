package com.codegym.controllers;

import com.codegym.model.ItemInCart;
import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/shoppingcart")
//@SessionAttributes("cart")
@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping("/ordernow/{id}")
    public ModelAndView orderProduct(@PathVariable Long id, HttpSession session){
        if(session.getAttribute("cart") == null) {
            List<ItemInCart> cart = new ArrayList<>();
            Product product = productService.findById(id);
            cart.add(new ItemInCart(product, 1));
            session.setAttribute("cart",cart);

        } else{
            List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");
            int index = isExisting(id, session);
            if (index == -1)
                cart.add(new ItemInCart(productService.findById(id), 1));
            else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
        }

        ModelAndView modelAndView = new ModelAndView("/shoppingCart/cart");
        return modelAndView;
   }


    private int isExisting(long id, HttpSession session) {
        List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");

        for (int i = 0; i < cart.size(); i++)

            if (cart.get(i).getProduct().getId() == id)
                return i;

        return -1;
    }

    @GetMapping("/edit-quantity/{id}")
    public ModelAndView editQuantity(@PathVariable Long id,HttpSession session){
        List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");
        int index= isExisting(id,session);
        ItemInCart item = cart.get(index);
        ModelAndView modelAndView= new ModelAndView("shoppingCart/editQuantity");
        modelAndView.addObject("item",item);
        return modelAndView;
    }

    @PostMapping("/update-quantity/{id}")
    public ModelAndView updateQuantity(@ModelAttribute("item") ItemInCart item,
                                 @PathVariable Long id,@RequestParam("quantity") Integer quantity, HttpSession session){

        List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");
        int index = isExisting(id,session);
        cart.get(index).setQuantity(quantity);
        session.setAttribute("cart",cart);
        ModelAndView modelAndView = new ModelAndView("shoppingCart/cart");

        return modelAndView;


    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProductForm(@PathVariable Long id, HttpSession session){
        List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");
        int index= isExisting(id,session);
        ItemInCart item = cart.get(index);
        ModelAndView modelAndView= new ModelAndView("shoppingCart/delete");
        modelAndView.addObject("item",item);
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id, @ModelAttribute("item") ItemInCart item, HttpSession session){
        List<ItemInCart> cart = (List<ItemInCart>) session.getAttribute("cart");
        int index= isExisting(id,session);
        cart.remove(cart.get(index));
        session.setAttribute("cart", cart);
        return "shoppingCart/cart";
    }

    @GetMapping("/deleteAll")
    public String deleteCart(HttpSession session){
        session.removeAttribute("cart");
        return "shoppingCart/cart";
    }
}
