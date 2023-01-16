
public class API {
private int id;
    private String[] web_pages;
    private String state_province;
    private String alpha_two_code;
    private String country;
    private String name;
    private String[] domains;
    
    
    
    public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getWeb_pages() {
		return web_pages;
	}
	public void setWeb_pages(String[] web_pages) {
		this.web_pages = web_pages;
	}
	public String getState_province() {
		return state_province;
	}
	public void setState_province(String state_province) {
		this.state_province = state_province;
	}
	public String getAlpha_two_code() {
		return alpha_two_code;
	}
	public void setAlpha_two_code(String alpha_two_code) {
		this.alpha_two_code = alpha_two_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @Override public String toString()
	    {
	        return "API [id="+ ", web_pages=" + web_pages
	            + ", state_province=" + state_province + ", alpha_two_code="
	            + alpha_two_code + ",country=" + country + ", name=" + name + "]";
	    }
	public String[] getDomains() {
		return domains;
	}
	public void setDomains(String[] domains) {
		this.domains = domains;
	}
	}

