package edu.westga.cs6312.files.model;

/**
 * Provides a model for working with teams, keeping track of their names, wins,
 * and losses.
 * 
 * @author Spencer Dent
 * @version 2021-03-06
 */
public class Team implements Comparable<Team> {
	private String name;
	private int numberOfWins;
	private int numberOfLosses;

	/**
	 * Creates a new team based off of the provided parameters.
	 * 
	 * @param newName           - the name of the new team
	 * @param newNumberOfWins   - the number of times the new team has won
	 * @param newNumberOfLosses - the number of times the new team has lost
	 * @precondition newName != null
	 * @throws IllegalArgumentException
	 */
	public Team(String newName, int newNumberOfWins, int newNumberOfLosses) {
		if (newName == null) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (newNumberOfWins < 0) {
			throw new IllegalArgumentException("Wins must be positive");
		}
		if (newNumberOfLosses < 0) {
			throw new IllegalArgumentException("Losses must be positive");
		}

		this.name = newName;
		this.numberOfWins = newNumberOfWins;
		this.numberOfLosses = newNumberOfLosses;
	}

	/**
	 * Returns the ratio of wins to total number of games where 0 is no games won
	 * and 1 is all games won.
	 * 
	 * @return the ratio of number of games won to total number of games
	 */
	public double getWinningPercentage() {
		double totalGames = (double) this.numberOfWins + this.numberOfLosses;

		if (totalGames == 0.0) {
			return totalGames;
		}

		return this.numberOfWins / totalGames;
	}

	/**
	 * Returns the number of times the team has won
	 * 
	 * @return the number of times the team has won
	 */
	public int getNumberOfWins() {
		return this.numberOfWins;
	}

	/**
	 * Returns a String which represents the state of the Team object, outlining
	 * each of its instance variables in a descriptive sentence.
	 * 
	 * @return the String representation of the Team object
	 */
	@Override
	public String toString() {
		return "The " + this.name + " with " + this.numberOfWins + " wins and " + this.numberOfLosses + " losses";
	}

	/**
	 * Compares two teams, returning an integer representing the order they should
	 * be sorted in. If the Team that contains the method precedes the Team passed
	 * into the method, it should return -1, 0 if they are the same, and 1 if it
	 * comes after.
	 * 
	 * @return an integer describing whether the team should come before or after
	 *         the other team
	 * @precondition otherTeam != null
	 * @throws IllegalArgumentException
	 */
	@Override
	public int compareTo(Team otherTeam) {
		if (otherTeam == null) {
			throw new IllegalArgumentException("Invalid team");
		}

		int comparison = Double.compare(otherTeam.getWinningPercentage(), this.getWinningPercentage());

		if (comparison != 0) {
			return comparison;
		}

		return Integer.compare(otherTeam.getNumberOfWins(), this.numberOfWins);
	}

	/**
	 * Takes a String and converts it into a Team object.
	 * 
	 * Must have 3 values separated by commas: the first value can be any string of
	 * non-comma characters, and the other two values must be able to be converted
	 * into integers (also no).
	 * 
	 * The catch and re-throw is deliberate in order to attach a more descriptive
	 * message to the error.
	 * 
	 * @param rawTeamString - the String to be converted into a new Team
	 * @return the new Team
	 * @precondition rawTeamString != null
	 * @precondition rawTeamString in form /^.*\s*,\s*-?\d+\s*,\s*-?\d+\s*$/
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public static Team parseTeam(String rawTeamString) throws IllegalArgumentException, NumberFormatException {
		if (rawTeamString == null) {
			throw new IllegalArgumentException("Invalid team");
		}

		rawTeamString = rawTeamString.trim();
		String[] rawTeamParts = rawTeamString.split(",");

		if (rawTeamParts.length != 3) {
			throw new IllegalArgumentException("Invalid team read");
		}

		String newTeamName = rawTeamParts[0].trim();

		int newTeamNumberOfWins = 0;
		try {
			newTeamNumberOfWins = Integer.parseInt(rawTeamParts[1].trim());
		} catch (NumberFormatException winsNotANumber) {
			throw new NumberFormatException("Wins must be a number");
		}

		int newTeamNumberOfLosses = 0;
		try {
			newTeamNumberOfLosses = Integer.parseInt(rawTeamParts[2].trim());
		} catch (NumberFormatException lossesNotANumber) {
			throw new NumberFormatException("Losses must be a number");
		}

		return new Team(newTeamName, newTeamNumberOfWins, newTeamNumberOfLosses);
	}
}
