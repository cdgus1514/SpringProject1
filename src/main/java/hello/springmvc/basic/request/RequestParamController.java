package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 리턴 응답을 http message로 반환됨
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){

        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {

        // int는 기본형으로 null 불가능
        // Integer는 객체형으로 null 가능

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paraMap) {

        log.info("username = {}, age = {}", paraMap.get("username"), paraMap.get("age"));
        return "ok";
    }


    /**
     * 객체를 생성 후 요청 파라미터 이름으로 객체의 프로퍼티를 찾아 해당 프로퍼티의 setter를 호출해서 값 바인딩
     * @param helloData
     * @return
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    //public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("hellodata = {}", helloData);

        return "ok";
    }


    @ResponseBody
    @RequestMapping("model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
