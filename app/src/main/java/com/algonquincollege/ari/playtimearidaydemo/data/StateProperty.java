package com.algonquincollege.ari.playtimearidaydemo.data;

/**
 * Author: Cong Tran
 * @param <T>
 */
public abstract class StateProperty<T> {
	
	private T state;
	public T getState() { return state; }
	protected void setState(T state) { this.state = state; }
	
	private T[] states;
	public T[] getStates() { return states; }
	protected void setStates(T[] states) { this.states = states; }

	public boolean isState(T state) {
		return this.state == state;
	}
	
	protected boolean hasStateMatchingInput(T in) {
		for (int i = 0; i < states.length; i++) {
			if (in == states[i]) return true;
		}
		return false;
	}
	
	// Constructor
	public StateProperty(T[] states, T initialState) {
		setStates(states);
		setState(initialState);
	}
	
	@Override
	public String toString() {
		return String.valueOf(state);
	}
	
}//end class CharacterStateProperty
