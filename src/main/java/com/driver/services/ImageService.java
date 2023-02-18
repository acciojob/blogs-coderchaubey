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
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
//        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
//
//        String splitD[]=screenDimensions.split("X");
//
//        String originalD[]=imageRepository2.findById(id).get().getDimensions().split("X");
//
//        //Of the parameters
//        int splitDIntFirst=Integer.parseInt(splitD[0]);
//        int splitDIntSecond=Integer.parseInt(splitD[1]);
//
//        //Of the original entity
//        int originalDFirst=Integer.parseInt(originalD[0]);
//        int originalDSecond=Integer.parseInt(originalD[1]);
//
//        //operation
//        int ansOfsplit=splitDIntFirst/originalDFirst;
//        int ansOfOriginal=splitDIntSecond/originalDSecond;
//
//
//
//
//        return ansOfsplit*ansOfOriginal;
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String [] scrarray = screenDimensions.split("X"); //A=Length   X    B=Breadth
//        if(!imageRepository2.findById(id).isPresent()){
//            throw new Exception();
//        }
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] imgarray = imageDimensions.split("X");

        int scrl = Integer.parseInt(scrarray[0]); //A -- > integer
        int scrb = Integer.parseInt(scrarray[1]); //B -- > integer

        int imgl = Integer.parseInt(imgarray[0]); //A -- > integer
        int imgb = Integer.parseInt(imgarray[1]); //B -- > integer

        return no_Images(scrl,scrb,imgl,imgb);

    }

    private int no_Images(int scrl, int scrb, int imgl, int imgb) {
        int lenC = scrl/imgl; //
        int lenB = scrb/imgb;
        return lenC*lenB;
    }
}
