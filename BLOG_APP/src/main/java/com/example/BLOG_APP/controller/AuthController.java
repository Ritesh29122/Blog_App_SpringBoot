        package com.example.BLOG_APP.controller;

        import com.example.BLOG_APP.payloads.JwtAuthRequest;
        import com.example.BLOG_APP.payloads.JwtAuthResponse;
        import com.example.BLOG_APP.security.JwtTokenHelper;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.BadCredentialsException;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.web.bind.annotation.*;
        //
        //@RestController
        //@RequestMapping("/api/auth")
        //public class AuthController {
        //
        //    private final AuthenticationManager authenticationManager;
        //    private final UserDetailsService userDetailsService;
        //    private final JwtTokenHelper jwtTokenHelper;
        //
        //    public AuthController(AuthenticationManager authenticationManager,
        //                          UserDetailsService userDetailsService,
        //                          JwtTokenHelper jwtTokenHelper) {
        //        this.authenticationManager = authenticationManager;
        //        this.userDetailsService = userDetailsService;
        //        this.jwtTokenHelper = jwtTokenHelper;
        //    }
        //
        //    @PostMapping("/login")
        //    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
        //        try {
        //            // Authenticate the user
        //            authenticationManager.authenticate(
        //                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        //            );
        //
        //            // Load user details
        //            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        //
        //            // Generate JWT token
        //            String token = jwtTokenHelper.generateToken(userDetails.getUsername());
        //            System.out.println("Generated JWT Token: " + token);  // Log the token
        //
        //            // Return the token in the response
        //            JwtAuthResponse response = new JwtAuthResponse(token);
        //            return ResponseEntity.ok(response);
        //
        //        } catch (BadCredentialsException e) {
        //            return ResponseEntity.status(401).body(new JwtAuthResponse("Invalid username or password"));
        //        }
        //    }
        //}
        @RestController
        @RequestMapping("api/auth")
        public class AuthController {

            private final AuthenticationManager authenticationManager;
            private final JwtTokenHelper jwtTokenHelper;

            public AuthController(AuthenticationManager authenticationManager, JwtTokenHelper jwtTokenHelper) {
                System.out.println("AuthController initialized");
                this.authenticationManager = authenticationManager;
                this.jwtTokenHelper = jwtTokenHelper;
            }

            @PostMapping("/login")
            public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authRequest) {
                System.out.println("Login API called");
                try {
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("USNM:" + authRequest.getUsername());
                    System.out.println("PWD:" + authRequest.getPassword());

                    // Generate the JWT token
                    String token = jwtTokenHelper.generateToken(authRequest.getUsername());
                    System.out.println("TOKEN HERE:" + token);

                    // Return the token in the response
                    JwtAuthResponse response = new JwtAuthResponse(token);
                    return ResponseEntity.ok(response);
                } catch (BadCredentialsException e) {
                    System.out.println("Invalid credentials for user: " + authRequest.getUsername());
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtAuthResponse("Invalid credentials"));
                }
            }
        }
