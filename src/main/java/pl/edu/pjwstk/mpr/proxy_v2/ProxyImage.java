package pl.edu.pjwstk.mpr.proxy_v2;

public class ProxyImage implements ImageInterface{

    private Image image;
    private int counter = 0;

    @Override
    public String getImage(){

        counter++;
        if (counter > 1){
            if (image == null){
                image = new Image();
            }
            return image.getImage();
        }
        return "LoRes proxy thumbnail ...";
    }
}