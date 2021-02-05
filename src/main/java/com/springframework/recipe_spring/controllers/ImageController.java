package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.service.ImageService;
import com.springframework.recipe_spring.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private RecipeService recipeService;
    private ImageService imageService;

    public ImageController(RecipeService recipeService,ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }



    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }


    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile")MultipartFile file) throws IOException {
        log.debug("Ok handle Image");
        imageService.saveImageFile(Long.valueOf(id),file);

        return "redirect:/recipe/show/"+id;
    }


    @GetMapping("recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        byte[] bytes = new byte[recipeCommand.getImage().length];

        int i = 0;

        for (Byte b : recipeCommand.getImage()){

            bytes[i++] = b;
        }

        response.setContentType("image/jpeg");
        InputStream in = new ByteArrayInputStream(bytes);
        IOUtils.copy(in,response.getOutputStream());


    }

}
