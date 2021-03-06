import controllers.*;
import freemarker.template.Configuration;
import models.Image;
import services.BootStrapServices;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Jhon on 4/7/2017.
 */
public class Main {
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    private static FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

    public static void main(String[] args) {
        BootStrapServices.getInstancia().init();
        /*Client cliente = new Client("client@gmail.com","","","", Date.valueOf(LocalDate.now()),"");
        Host host = new Host("host@gmail.com","","","", Date.valueOf(LocalDate.now()),"");
        ClientDao.getInstance().create(cliente);
        HostDao.getInstance().create(host);

        System.out.println(cliente);
        System.out.println(host);

        ArrayList<User> usuarios = new ArrayList<>();
        usuarios.add(cliente);
        usuarios.add(host);

        usuarios.stream().forEach(System.out::println);*/


        initSpark();

    }

    private static void initSpark() {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            List<Image> images = ImageDao.getInstance().findAll();
            /*String texto = "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";
            images.add(new String(texto));
            images.add(new String(texto));
            images.add(new String(texto));*/
            attributes.put("images",images);

            System.out.println("images: "+ImageDao.getInstance().findAll());

            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        post("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                ImageDao.getInstance().create(new Image(IOUtils.toByteArray(is)));;
            }
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);


    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
