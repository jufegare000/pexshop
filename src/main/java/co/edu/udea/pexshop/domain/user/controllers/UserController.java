package co.edu.udea.pexshop.domain.user.controllers;

import co.edu.udea.pexshop.domain.user.model.dto.RegisterUserDTO;
import co.edu.udea.pexshop.domain.user.model.dto.UserResponseDTO;

import co.edu.udea.pexshop.domain.user.model.entity.User;
import co.edu.udea.pexshop.domain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import co.edu.udea.pexshop.domain.user.service.UserServiceImpl;
import javassist.tools.web.BadHttpRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users/sign-in")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private IUserService iUserService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) {
        UserResponseDTO userResponseDTO = null;
        Map<String, Object> response = new HashMap<>();

        try{
            userResponseDTO = userService.createUserDTO(registerUserDTO);
            return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            response.put("msj:", "Error registering user");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/v1/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id){
        User user = iUserService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
