package fr.aang.gvote.config;

public class WebServ {

	private String	_name;
	private	int		_delay;
	private	int		_server_id;
	private	String	_url_vote;
	private	String	_url_check;
	
	public WebServ(int server_id) {
		_server_id = server_id;
	}
	
	// SETTERS
	
	public void	setName(String name) {
		_name = name;
	}
	
	public void	setDelay(int delay) {
		_delay = delay;
	}
	
	public void setUrlVote(String url_vote) {
		_url_vote = url_vote.replace("{server}", ("" + _server_id));
	}
	
	public void setUrlCheck(String url_check) {
		_url_check = url_check.replace("{server}", ("" + _server_id));
	}
	
	// GETTERS
	
	public String	getName() {
		return _name;
	}
	
	public int		getDelay() {
		return _delay;
	}
	
	public String	getUrlVote(String player_name) {
		return _url_vote.replace("{player}", player_name);
	}
	
	public String	getUrlCheck(String ip) {
		return _url_check.replace("{ip}", ip);
	}
	
}
