package com.rest.template.controller;

import com.rest.template.model.TestDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 测试接口
 */
@Controller
public class TestController {

    /**
     * get方法测试
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("testGet")
    public TestDTO testGet(HttpServletRequest request, HttpServletResponse response) {
        TestDTO TestDTO = new TestDTO();
        TestDTO.setId(1);
        TestDTO.setName("get");
        return TestDTO;
    }
    @RequestMapping("testGet2")
    public String testGet2(HttpServletRequest request, HttpServletResponse response) {

        return "ss";
    }

    /**
     * post方法
     *
     * @return
     */
    @PostMapping("testPost")
    public TestDTO testPost(HttpServletRequest request, HttpServletResponse response, @RequestBody TestDTO testDTO) {
        testDTO.setName("from server " + testDTO.getName());
        return testDTO;
    }

    /**
     * post 方法传值
     *
     * @param id
     * @param name
     * @return
     */
    @PostMapping("testPostParam/{id}")
    public String testPostParam(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name") String name)
            throws IOException {
        System.out.println("Post id:" + id);
        System.out.println("Post name:" + name);
        String url = "http://127.0.0.1:8080/testGet2";
        //String http = HttpUtils.getHttp(url, null);
        String s = HttpClientUtil.sendHttp(HttpMethod.GET, url, null);
        //String response = HttpClientUtil.sendHttp(HttpMethod.GET, url, null);
        System.out.println("response=="+s);
        return "post succ";
    }

    @ResponseBody
    @PostMapping("testPostParam2")
    public String testPostParam2(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Post id:" );
        System.out.println("Post name:" );
        //String bodyString = getBodyString(request);
        return "post succ";
    }
    @PostMapping("testPostParam3")
    public String testPostParam3(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Post id:" );
        System.out.println("Post name:" );
        String bodyString = readAsChars(request);
        return "post succ";
    }

    /**
     * post 方法传值
     *
     * @param id
     * @param name
     * @return
     */
    @PutMapping("testPut")
    public String testPut(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name") String name) {
        System.out.println("put id:" + id);
        System.out.println("put name:" + name);
        return "del succ";
    }

    /**
     * del方法传值
     *
     * @param id
     * @return
     */
    @DeleteMapping("testDel")
    public String testDel(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
        System.out.println("del id:" + id);
        return "del succ";
    }

    public static String ReadAsChars2(HttpServletRequest request) {
        InputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1; ) {
                sb.append(new String(b, 0, n));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String readAsChars(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            if (br == null) {
                return null;
            }
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String getBodyString(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String s1 = new String(bytes);

//            if (inputStream == null) {
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private String readResponseBody(HttpServletResponse response) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        OutputStream out = response.getOutputStream();
        byteArrayOutputStream.writeTo(out);

        byte[] bytes = byteArrayOutputStream.toByteArray();
        String s = new String(bytes);
        try {

            return out.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }

        }
    }
}
