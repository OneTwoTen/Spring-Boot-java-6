// package com.Java6.rest.controller;

// import javax.servlet.http.HttpServletRequest;

// import com.Java6.entity.Account;
// import com.Java6.security.JwtService;
// import com.Java6.service.AccountService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(value = "/rest")
// public class AccountRestController {

//     @Autowired
//     private JwtService jwtService;
//     @Autowired
//     private AccountService accountService;

//     @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
//     public ResponseEntity<Object> getAccountById(@PathVariable String id) {

//         System.out.println("login");
//         Account account = accountService.findById(id);
//         if (account != null) {
//             return new ResponseEntity<Object>(account, HttpStatus.OK);
//         }
//         return new ResponseEntity<Object>("Not found User", HttpStatus.NO_CONTENT);
//     }

//     @RequestMapping(value = "/login", method = RequestMethod.POST)
//     public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Account account) {
//         String result = "";
//         HttpStatus httpStatus = null;
//         try {
//             if (accountService.checkLogin(account)) {
//                 result = jwtService.generateTokenLogin(account.getUsername());
//                 httpStatus = HttpStatus.OK;
//             } else {
//                 result = "Wrong userId and password";
//                 httpStatus = HttpStatus.BAD_REQUEST;
//             }
//         } catch (Exception ex) {
//             result = "Server Error";
//             httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            
//         }
//         return new ResponseEntity<String>(result, httpStatus);
//     }
// }
