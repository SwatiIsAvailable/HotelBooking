package com.app.service;

import java.sql.Date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.app.exception.*;
import com.app.dao.BookingDao;
import com.app.dao.UserDao;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.EditPassDTO;
import com.app.dto.OTPVerificationDTO;
import com.app.dto.SignupResponse;
import com.app.dto.Signuprequest;
import com.app.entities.BookingTbl;
import com.app.entities.RoomType;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;

@Transactional
@Service
public class UserServiceImpl implements UserService { 
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookingDao bookDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public AuthResp singInUser(AuthRequest request) {
		User user=userDao.findByEmailAndPassword(request.getEmail(),request.getPassword())
				.orElseThrow(()->new ResourceNotFoundException("invalid email and password"));
		return mapper.map(user, AuthResp.class);
	}
	
	
	User user = null;
	public boolean authenticate(String username , String password)
	{
	     User user = userDao.findByEmail(username);
	     this.user = user;
	     return user.getEmail().equals(username) && user.getPassword().equals(password);
	}
	public String getUserRole()
	{
	     return user.getRole().toString();	
	}
	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUserDetail(User user) {
		User founduser = userDao.findById(user.getId()).orElseThrow(()-> new
				ResourceNotFoundException("Invalid Emp ID !!!!!"));
				
		return userDao.save(user);
	}
	@Override
	public String deleteUserDetails(Long userId) {
		// TODO Auto-generated method stub
		User founduser=userDao.findById(userId).orElseThrow(()
				->new  ResourceNotFoundException("Invalid Emp ID !!!!!"));
		userDao.deleteById(userId);
		return "user Deatils deleted successfully !!!";
	}
	@Override
	public User getUserDetails(Long userId) {
		// TODO Auto-generated method stub
		
		return userDao.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("Invalid Emp ID !!!!!"));
	}
	
	
	public int bookingPrice(LocalDate checkIn,LocalDate checkOut,String type)
	{
		int days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);
		System.out.println(days);
		int price = 0;
		if(type.equals("AC"))
		{
			price = days*3000;
			System.out.println("in AC");
		}
		else if(type.equals("NONAC"))
		{
			price = days*1000;
		}
		else if(type.equals("DELUXE"))
		{
			price = days*5000;
		}
		else
		{
			price = days*0;
			System.out.println("in else");
		}
		bookDao.save(new BookingTbl(checkIn,checkOut,java.time.LocalDate.now(),price));
		return price;
	}
	//-------------------------------------------------
	//-------------------------------------------------
	
	
	
	
	Signuprequest userObj=new Signuprequest();
	private Map<String,String>otpMap=new HashMap<>();
	
	
	public void sendOTPAndStoreUserdata(Signuprequest userRegistrationDTO) {
		userObj=userRegistrationDTO;
		String otp=generateOTP();
		sendOTPEmail(userRegistrationDTO.getEmail(),otp);
		
	}
	
	
	@Override
	public boolean verifyOTP(OTPVerificationDTO otpVerificationDTO) {
		// TODO Auto-generated method stub
		
		String storedOTP=otpMap.get(userObj.getEmail());
		otpMap=null;
		return storedOTP !=null && storedOTP.equals(otpVerificationDTO.getOtp());
	}
	
	
	private String generateOTP() {
		int otpLength=6;
		String numbers="0123456789";
		StringBuilder otp=new StringBuilder();
		for(int i=0;i<otpLength;i++) {
			int index=(int)(Math.random()*numbers.length());
			otp.append(numbers.charAt(index));
		}
		return otp.toString();
	}
	
	
	
	private void sendOTPEmail(String email,String otp) {
		MimeMessage message=javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			helper.setTo(email);
			helper.setSubject("OTP Verification");
			helper.setText("Your OTP for registration is "+otp);
			javaMailSender.send(message);
			
		}catch(MessagingException e) {
			System.out.println("in the send otp");
		}
	}
	
	
	
	@Override
	public void getOtpForForgotPass(String emailId) {
		// TODO Auto-generated method stub
		 User user=userDao.findByEmail(emailId);
		 userObj.setEmail(emailId);
		 if(user.getEmail()!=null) 
		 {
			 String otp=generateOTP();
			 sendOTPEmail(emailId,otp);
			 otpMap.put(emailId, otp);
				System.out.println("Inside Map :"+otpMap);
		}	 else if(user.getEmail()==null)throw new ResourceNotFoundException("User is not exit from userService calss ");
	}
	
	@Override
	public SignupResponse storeUserDataWithNewPass(EditPassDTO newPass) {
		// TODO Auto-generated method stub
		User user=userDao.findByEmail(userObj.getEmail());//fetch user details based on email
		if(user!=null) {
			user.setPassword(newPass.getNewPassword());//Update the password
			User persistentUser=userDao.save(user);//Save the updated user details
			
			return mapper.map(persistentUser,SignupResponse.class);//map to response object
			
		}else {
			throw new ResourceNotFoundException("User Does not exits from userServiceImpl Class");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}

