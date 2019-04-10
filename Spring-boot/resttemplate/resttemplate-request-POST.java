-------------------------
POST					 |
-------------------------
	URI postForLocation(String url, @Nullable Object request, Object... uriVariables) throws RestClientException;
	URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables)throws RestClientException;
	URI postForLocation(URI url, @Nullable Object request) throws RestClientException;

	<T> T postForObject(String url, @Nullable Object request, Class<T> responseType,Object... uriVariables) throws RestClientException;
	<T> T postForObject(String url, @Nullable Object request, Class<T> responseType,Map<String, ?> uriVariables) throws RestClientException;
	<T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException;

	<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,Object... uriVariables) throws RestClientException;
	<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,Map<String, ?> uriVariables) throws RestClientException;
	<T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)throws RestClientException;



