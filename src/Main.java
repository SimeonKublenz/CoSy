import Memory.Color;
import Memory.DrawableObjects;
import Memory.Form;

import java.util.ArrayList;
import java.util.Random;

public class Main {


    /**
     *
     */
    private final Random rand = new Random();


    public DrawableObjects[][] randomPicGenerator(final int pNumberOfObjects, final Form pForm, final Color pColor) {

        ArrayList<DrawableObjects> differentObjects = new ArrayList<>();
        differentObjects.add(new DrawableObjects(pColor,pForm));

        // randomly generated objects
        for (int i = 0; i < 5; i++){
            boolean alreadyUsed = true;
            Form form = pForm;
            Color color = pColor;
            while (alreadyUsed){
                alreadyUsed = false;
                form = Form.values()[rand.nextInt(3)];
                color = Color.values()[rand.nextInt(4)];

                for (DrawableObjects drawableObjects : differentObjects){
                    if (drawableObjects.getForm().equals(form) && drawableObjects.getColor().equals(color)){
                        alreadyUsed = true;
                    }
                }
            }
            differentObjects.add(new DrawableObjects(color, form));
        }

        DrawableObjects[][] picture = new DrawableObjects[20][15];

        int numberOfObjects;

        if (pNumberOfObjects < 1 || pNumberOfObjects > 20) {
            numberOfObjects = 10;
        } else {
            numberOfObjects = pNumberOfObjects;
        }
        for (int j = 0; j < numberOfObjects; j++) {
            picture = fillArrayWithObjects(picture, differentObjects.get(0));
        }

        for (int i = 1; i < differentObjects.size(); i++) {

            numberOfObjects = rand.nextInt(20);

            for (int j = 0; j < numberOfObjects; j++) {
                picture = fillArrayWithObjects(picture, differentObjects.get(i));
            }
        }
        return picture;
    }


    private DrawableObjects[][] fillArrayWithObjects(final DrawableObjects[][] pArray, final DrawableObjects pObject) {
        DrawableObjects[][] array = pArray;

        int column = rand.nextInt(20);
        int row = rand.nextInt(15);

        if (array[column][row] != null) {
            fillArrayWithObjects(array, pObject);
        } else {
            array[column][row] = pObject;
        }
        return array;
    }




    public void print(DrawableObjects[][] array, Form pform, Color pColor) {

        int counter = 0;
        for (DrawableObjects[] column : array) {
            for (DrawableObjects row : column) {
                if (row == null) {
                    System.out.print(" EMPTY || ");
                } else {
                    String form = "";
                    String color = "";
                    switch (row.getColor()){
                        case GREEN: color = "GR";break;
                        case RED: color = "RE";break;
                        case BLUE: color = "BU";break;
                        case BLACK: color = "BA";break;
                        case YELLOW: color = "YE";
                    }
                    switch (row.getForm()){
                        case HEXAGON: form = "HEX";break;
                        case SQUARE: form = "SQU";break;
                        case CIRCLE: form = "CIR";break;
                        case OCTAGON: form = "OCT";
                    }
                    System.out.print( color + ":" + form + " || ");

                    if (row.getColor().equals(pColor) && row.getForm().equals(pform)) {
                        counter++;
                    }
                }
            }
            System.out.println("");
        }
    }




    public static void main(String[] args) {

        Main main = new Main();

        DrawableObjects blueC = new DrawableObjects(Color.BLUE, Form.CIRCLE);
        DrawableObjects blackC = new DrawableObjects(Color.BLACK, Form.CIRCLE);
        DrawableObjects redC = new DrawableObjects(Color.RED, Form.CIRCLE);
        DrawableObjects yellowS = new DrawableObjects(Color.YELLOW, Form.SQUARE);
        DrawableObjects blueS = new DrawableObjects(Color.BLUE, Form.SQUARE);
        DrawableObjects redH = new DrawableObjects(Color.RED, Form.HEXAGON);

        DrawableObjects[][] picture = {
                {null, null, null, yellowS, null, null, null, null, null, null, null, blueC, null, null, null},
                {null, yellowS, null, null, yellowS, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, blueC, null, null, null, null, null, null, null, null},
                {redC, null, null, redH, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, yellowS, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, blackC, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, blueC, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, blackC, null, blueS, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, blueC, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, blackC, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, redC, null, null},
                {null, null, null, null, null, null, blueC, null, null, null, null, null, null, null, null},
                {null, null, null, blueS, null, null, null, null, null, null, null, null, null, null, null},
                {null, redC, null, null, null, null, null, null, redH, null, null, null, null, null, null},
                {null, null, null, null, null, redC, null, null, null, null, null, null, blueS, null, null},
                {null, null, null, null, null, null, null, null, null, null, blueC, null, null, null, null},
                {null, redH, null, null, null, null, null, null, null, null, null, null, null, null, null}
        };

        main.print(picture,Form.CIRCLE,Color.BLUE);

        CognitiveSystem workload = new CognitiveSystem(picture, blueC);
        workload.howmany();
        workload.getFromMemory(Form.CIRCLE, Color.BLUE);
        workload.getFromMemory(Form.CIRCLE, Color.BLACK);

        System.out.println("");
        System.out.println("");

        DrawableObjects[][] randmonPicture = main.randomPicGenerator(7,Form.CIRCLE,Color.GREEN);
        main.print(randmonPicture,Form.CIRCLE,Color.GREEN);

        CognitiveSystem workload2 = new CognitiveSystem(randmonPicture, new DrawableObjects(Color.GREEN,Form.CIRCLE));
        workload2.howmany();
        workload2.getFromMemory(Form.CIRCLE, Color.GREEN);
        workload2.getFromMemory(Form.HEXAGON, Color.BLUE);
    }
}
