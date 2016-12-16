## REST 客户端 ##

作为客户端，编写与 REST 资源交互的代码可能会比较乏味，并且所编写的代码都是样板式的（详见 client.BasicClient）。鉴于在资源使用上有如此之多的样板代码，Spring 提供的 RestTemplate 能够在使用 RESTful 资源时免于编写那些乏味的代码。

### RestTemplate 的操作

RestTemplate 定义了 36 个与 REST 资源交互的方法，其中的大多数都对应于 HTTP 的方法。其实，这里面只有 11 个独立的方法，其中十个有三个重载形式，而第十一个则重载了六次。如下表：

	  方法                               描述
	delete()                在特定的 URL 上对资源执行 HTTP DELETE 操作
	exchange()				在 URL 上执行特定的 HTTP 方法，返回包含对象的 ResponseEntity，这个对象是从响应体中映射得到的
	execute()				在 URL 上执行特定的 HTTP 方法，返回一个从响应体映射得到的对象
	getForEntity()			发送一个 HTTP GET 请求，返回的 ResponseEntity 包含了响应体所映射成的对象
	getForObject()			发送一个 HTTP GET 请求，返回的请求体将映射为一个对象
	headForHeaders()		发送 HTTP HEAD 请求，返回包含特定资源 URL 的 HTTP 头
	optionsForAllow()		发送 HTTP OPTIONS 请求，返回包含特定 URL 的 Allow 头信息
	postForEntity()			POST 数据到一个 URL，返回包含一个对象的 ResponseEntity，这个对象是从响应体中映射得到的
	postForObject()			POST 数据到一个 URL，返回根据响应体匹配形成的对象
	postForLocation()		POST 数据到一个 URL，返回新创建资源的 URL
	put()					PUT 资源到特定的 URL

除了 TRACE 意外，RestTemplate 涵盖了所有的 HTTP 动作。除此之外，execute() 和 exchange() 提供了较低层次的通用方法来使用任意的 HTTP 方法。表中的大多数操作都以以下三种形式进行了重载：

* 一个使用了 java.net.URI 作为 URL 格式，不支持参数化 URL
* 一个使用了 String 作为 URL 格式，并使用 Map 指明 URL 参数
* 一个使用了 String 作为 URL 格式，并使用可变参数列表指明 URL 参数