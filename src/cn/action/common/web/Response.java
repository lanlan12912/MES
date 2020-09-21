package cn.action.common.web;

public class Response {
	private String accept;// 请求头,代表发送端（客户端）希望接受的数据类型。(text/html,application/xhtml+xml,application/xml;q=0.9)
	private String json;
	private String url;

	public Response(String accept, String json, String url) {
		this.accept = accept;
		this.json = json;
		this.url = url;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String CreateResponse() {

		if (this.accept.contains("json")) {
			return this.json;
		} else {
			return this.url;
		}
	}

	public static String CreateResponse(String accept, String json, String url) {
		if (accept.contains("json")) {
			return json;
		} else {
			return url;
		}
	}
}
