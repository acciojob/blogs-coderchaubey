package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog originalBlog=blogRepository2.findById(blogId).get();

        image.setBlog(originalBlog);
        blogRepository2.save(originalBlog);
        return image;
    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count=0;
        String splitD[]=screenDimensions.split("X");

        String originalD[]=imageRepository2.findById(id).get().getDimensions().split("X");

        //Of the parameters
        int splitDIntFirst=Integer.parseInt(splitD[0]);
        int splitDIntSecond=Integer.parseInt(splitD[1]);

        //Of the original entity
        int originalDFirst=Integer.parseInt(originalD[0]);
        int originalDSecond=Integer.parseInt(originalD[1]);

        //operation
        int ansOfsplit=splitDIntFirst/originalDFirst;
        int ansOfOriginal=splitDIntSecond/originalDSecond;

        count=ansOfsplit*ansOfOriginal;


        return count;
    }
}
