package game.backend;

/* Class for a single card */
public class Card {
    private String question;
    private String answer;
    private String choices;
    private int points;
    private String category;
        

    /* Default constructor */
    public Card() {
    	question = null;
    	answer = null;
    	choices = null;
    	points = 0;
    	category = null;
    }
    
    /* Constructor */
    public Card(String question, String answer, String choices, int points, String category) {
    	
    	/* Makes sure the the user actually enters a question */
    	this.question = (question.length() > 0) ? question : null;
    	
    	/* Makes sure the the user actually enters a answer */
    	this.answer = (answer.length() > 0) ? answer : null;
    	
    	/* Makes sure the the user actually enters choices */
    	this.choices = (choices.length() > 0) ? choices : null;
    	
    	/* Makes sure the the user actually enters a choice */
    	this.category = (category.length() > 0) ? category : null;
    	
    	/* Makes sure the the user actually enters valid points */
    	this.points = (points < 51 && points > -51) ? points : 0;
    }

    /* Returns the question */
    public String getQuestion() {
        return question;
    }

    /* Returns the answer */
    public String getAnswer() {
        return answer;
    }

    /* Returns the choices */
    public String getChoices() {
        return choices;
    }

    /* Returns the points */
    public int getPoints() {
        return points;
    }
    
    /* Returns the category */
    public String getCategory() {
    	return category;
    }
}
