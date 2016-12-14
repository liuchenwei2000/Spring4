## 使用 Spring MVC 创建 REST API ##

近几年来，以信息为中心的表述性状态转移（Representational State Transfer,REST）已成为替换传统 SOAP Web 服务的流行方案。SOAP 一般会关注行为和处理，而 REST 关注的是要处理的数据。

对于许多应用程序而言，使用 SOAP 可能会有些大材小用了，而 REST 提供了一个更简单的可选方案。另外，很多的现代化应用都会有移动或富 JavaScript 客户端，它们都会使用运行在服务器上的 REST API。

### REST 基础知识

REST 不是另一种类型的远程过程调用（RPC），它与 RPC 几乎没有任何关系。RPC 是面向服务的，并关注于行为和动作；而 REST 是面向资源的，强调描述应用程序的事物和名词。

* 表述性（Representational） REST 资源实际上可以用各种形式来进行表述，包括 XML、JSON 甚至 HTML——最适合资源使用者的任意形式。

* 状态（State） 当使用 REST 的时候，更关注资源的状态而不是对资源采取的行为。

* 转移（Transfer） REST 涉及转移资源数据，它以某种表述性形式从一个应用转移到另一个应用。

REST 就是将资源的状态以最合适的客户端或服务端的形式从服务端转移到客户端（或者反过来）。在 REST 中，资源通过 URL 进行识别和定位。REST 中会有行为，它们是通过 HTTP 方法来定义的。具体的说，就是 GET、POST、PUT、DELETE、PATCH 以及其他的 HTTP 方法构成了 REST 中的动作。这些 HTTP 方法通常会匹配如下的 CRUD 操作：

* Create：POST
* Read：GET
* Update：PUT 或 PATCH
* Delete：DELETE

上面的映射关系也并不是严格的限制，有时候，PUT 可以用来创建新资源，POST 可以用来更新资源。  

Spring 对 REST 的支持是构建在 SpringMVC 之上的。
