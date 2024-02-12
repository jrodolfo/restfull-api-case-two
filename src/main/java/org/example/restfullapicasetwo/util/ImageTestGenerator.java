package org.example.restfullapicasetwo.util;

import org.example.restfullapicasetwo.model.CatalogImage;
import org.example.restfullapicasetwo.model.CatalogMetaDatum;

import java.util.ArrayList;
import java.util.List;

public class ImageTestGenerator {

    static final String URL = "http://www.nowhere.com/image-binary-url/image";

    public static List<CatalogImage> generateTestImages() {

        List<CatalogImage> images = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            CatalogImage image = new CatalogImage();
            List<CatalogMetaDatum> metaData = new ArrayList<>();

            for (int k = 0; k < 10; k++) {
                CatalogMetaDatum item = new CatalogMetaDatum();
                item.setName("name" + k);
                item.setValue("value" + k);
                metaData.add(item);
            }

            image.setImageId(Integer.toString(i));
            image.setMetaData(metaData);
            image.setImageFormat("png");
            image.setImagePath(URL + i + "." + image.getImageFormat());
            images.add(image);
        }
        return images;
    }

    public static CatalogImage generateTestImage(String imageId) {

        CatalogImage image = new CatalogImage();
        List<CatalogMetaDatum> metaData = new ArrayList<>();

        for (int k = 0; k < 10; k++) {
            CatalogMetaDatum item = new CatalogMetaDatum();
            item.setName("name" + k);
            item.setValue("value" + k);
            metaData.add(item);
        }

        image.setImageId(imageId);
        image.setMetaData(metaData);
        image.setImageFormat("png");
        image.setImagePath(URL + imageId + ".png");

        return image;
    }
}
