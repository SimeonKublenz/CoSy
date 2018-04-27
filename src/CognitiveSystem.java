import Memory.Color;
import Memory.DrawableObjects;
import Memory.Form;

import java.util.ArrayList;

public class CognitiveSystem {

	int timer = 0;
	final int maxTime = 1600;
	final int nullTime = 1;
	final int jumpTime = 10;
	final int registerTime = 15;

	ArrayList<DrawableObjects> visual = new ArrayList<DrawableObjects>();
	ArrayList<DrawableObjects> memory = new ArrayList<DrawableObjects>();

	boolean unsure1;
	boolean unsure2;

	DrawableObjects firstQuestion;


	public CognitiveSystem(final DrawableObjects[][] picture, final DrawableObjects question) {
		firstQuestion = question;
		for (DrawableObjects[] row : picture) {
			for (DrawableObjects cell : row) {
				if (timer > maxTime - nullTime) {
					break;
				}
				if (cell == null) {
					timer += nullTime;
				} else if ((cell.getColor().equals(question.getColor())) && (cell.getForm().equals(question.getForm()))) {
					if (timer >= maxTime - registerTime) {
						unsure1 = true;
						unsure2 = true;
						break;
					}
					add(cell, 1);
					timer += registerTime;
				} else {
					if (timer >= maxTime - jumpTime) {
						unsure2 = true;
						break;
					}
					DrawableObjects x = new DrawableObjects(Color.UNDEFINED, Form.UNDEFINED);
					add(x, 1);
					timer += jumpTime;
				}
			}
		}
		for (DrawableObjects[] row : picture) {
			for (DrawableObjects cell : row) {
				if (timer > maxTime - nullTime) {
					unsure2 = true;
					break;
				}
				if (cell == null) {
					timer += nullTime;
				} else if ((cell.getColor().equals(question.getColor())) && (cell.getForm().equals(question.getForm()))) {
					timer += nullTime;
				} else {
					if (timer >= maxTime - registerTime) {
						unsure2 = true;
						break;
					}
					add(cell, 1);
					timer += registerTime;
				}
			}
		}
	}

	public void add(DrawableObjects object, int number) {
		while (number > 0) {
			visual.add(object);
			number--;
		}
	}

	public boolean remove(DrawableObjects object, int number) {
		boolean worked = false;
		while (number > 0) {
			worked = visual.remove(object);
			number--;
		}
		return worked;
	}

	public void howmany() {
		for (DrawableObjects cell : visual) {
			boolean changed = false;
			for (DrawableObjects object : memory) {
				if ((cell.getColor().equals(object.getColor())) && (cell.getForm().equals(object.getForm()))) {
					object.raiseNumber();
					changed = true;
				}
			}
			if (!changed) {
				memory.add(new DrawableObjects(cell.getColor(), cell.getForm(), 1));
			}
			changed = false;
		}
	}

	public int getFromMemory(final Form form, final Color color) {
		for (DrawableObjects objectType : memory) {
			if ((objectType.getForm().equals(form)) && (objectType.getColor().equals(color))) {
				String result = "There were " + objectType.getNumber() + " items of the type " + color + " " + form + " on the picture!";
				if ((firstQuestion.getForm().equals(form)) && (firstQuestion.getColor().equals(color))) {
					if (unsure1) {
						System.out.println(result + " I think so");
					} else {
						System.out.println(result);
					}
				} else {
					if (unsure2) {
						System.out.println(result + " I think so");
					} else {
						System.out.println(result);
					}
				}
				return objectType.getNumber();
			}
		}
		if (unsure2) {
			System.out.println(("There were no items of the type " + color + " " + form + " on the picture! I think so"));
		} else {
			System.out.println(("There were no items of the type " + color + " " + form + " on the picture!"));
		}
		return 0;
	}


}
