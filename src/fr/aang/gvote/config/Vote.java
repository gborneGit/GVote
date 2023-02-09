package fr.aang.gvote.config;

import java.util.Date;

public class Vote {
	
	private Date	_vote1;
	private Date	_vote2;
	private Date	_vote3;
	
	public	Vote() {
		_vote1 = null;
		_vote2 = null;
		_vote3 = null;
	}
	
	// SETTERS
	public void setVote1(Date time) {
		_vote1 = time;
	}
	
	public void setVote2(Date time) {
		_vote2 = time;
	}
	
	public void setVote3(Date time) {
		_vote3 = time;
	}
	
	// GETTERS
	public Date	getVote1() {
		return _vote1;
	}
	
	public Date	getVote2() {
		return _vote2;
	}
	
	public Date	getVote3() {
		return _vote3;
	}

}
