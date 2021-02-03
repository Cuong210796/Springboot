package springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

interface Outfit {
    public void wear();
}

interface HairStyle {
    public void Hair();
}

@Component
class AoPhao implements Outfit {

    @Override
    public void wear() {
        System.out.println("toi mac ao phao");
    }
}

@Component
class Toc implements HairStyle {

    @Override
    public void Hair() {
        System.out.println("toc");
    }
}

@Component
class Men {
    public Outfit outfit;
    public HairStyle hairStyle;

    public Men(Outfit outfit, HairStyle hairStyle) {
        this.outfit = outfit;
        this.hairStyle = hairStyle;
    }
}

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(DemoApplication.class, args);
        Men cuong = container.getBean(Men.class);
        cuong.hairStyle.Hair();
        cuong.outfit.wear();
    }
}
