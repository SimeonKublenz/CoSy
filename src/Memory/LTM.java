package Memory;

import java.util.HashMap;

public class LTM {

    private Pattern[] patterns;

    private HashMap<DrawableObjects, Integer> answers;

    public int getAnswer(DrawableObjects object) {
        return answers.get(object);
    }

    public void setAnswers(DrawableObjects object, int counter) {
        answers.put(object, counter);
    }
}
