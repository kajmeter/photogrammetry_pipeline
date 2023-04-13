import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pml_reducing {
    public static String in;
    public static List<String> camera_names = new ArrayList<>();
    public static boolean is_even = false;
    public static int to_be_divided_by = 20;
    public static void main(String[] args) throws IOException {
        //load bundle
        Path file = Path.of("C:\\Users\\Kajtek\\Desktop\\PIPELINEPROJ\\" +
                "texturing_station\\PyMeshLab_raster_reducing\\src\\fixed_focus_out.mlp");

        //to string
        in = Files.readString(file);
       // System.out.println(in);

        //adding cameras to the list
        Pattern camera_bracket_pattern = Pattern.compile("\\<MLRaster label=\"(.*?)\"\\>");
        Matcher matcher = camera_bracket_pattern.matcher(in);
        while(matcher.find()){
            camera_names.add(matcher.group(1));
        }

        // checking if number of cameras is even
        int divide_number = camera_names.size()/2;
        if(divide_number*2== camera_names.size()){
            is_even = true;
        }


        //reducing camera_names list
        int iteration_number =0;
        for(Iterator<String> i = camera_names.iterator();i.hasNext();){
            //stopwatch fun
            i.next();
            if(iteration_number%to_be_divided_by ==0){
                i.remove();
            }
            iteration_number++;
        }

        //deleting picked cameras from imported pml file
        for(int i = 0;i< camera_names.size();i++){
            in = in.replaceAll("(?s)" +
                            "<MLRaster label=\""+camera_names.get(i)+"\">" +
                            ".*?" +
                            "</MLRaster>"
                    , "");
        }

        //EXPORT FIX PYMESHLAB
        //resolution
        String Viewport = "3078 871";
        String CenterPx = "1539 435";
        in = in.replaceAll("(?<=CenterPx=\").*?(?=\")", CenterPx);
        in = in.replaceAll("(?<=ViewportPx=\").*?(?=\")", Viewport);
        //EXPORT FIX PYMESHLAB

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "C:\\Users\\Kajtek\\Desktop\\PIPELINEPROJ\\texturing_station\\PyMeshLab_raster_reducing\\" +
                            "src\\reduced_fixed_focus_out.mlp"
            ));
            writer.write(in);
            writer.close();
        }catch (IOException e){

        }


     //   str = str.replaceAll("(?s)<b>.*?</b>", "");
        //test
        for(int i=0;i<camera_names.size();i++){
            System.out.println(camera_names.get(i));
        }
        System.out.println("\nCount : "+camera_names.size());
        System.out.println("Is even : "+is_even);
    }
}
