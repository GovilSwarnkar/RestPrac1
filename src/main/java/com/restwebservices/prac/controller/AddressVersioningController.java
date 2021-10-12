package com.restwebservices.prac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservices.prac.bean.Address;
import com.restwebservices.prac.bean.Address1;
import com.restwebservices.prac.bean.Address2;

@RestController
public class AddressVersioningController {

	//URI versioning - Twitter
	@GetMapping("/v1/address")
	public Address1 getAddressV1() {
		return new Address1("Brookfield, Banglore");
	}
	
	@GetMapping("/v2/address")
	public Address2 getAddressV2() {
		return new Address2(new Address("Brookfield", "Banglore"));
	}
	
	//Request param versioning - Amazon
	@GetMapping(value = "/address/param", params = "version=1")
	public Address1 getAddressParamV1() {
		return new Address1("Brookfield, Banglore");
	}
	
	@GetMapping(value = "/address/param", params = "version=2")
	public Address2 getAddressParamV2() {
		return new Address2(new Address("Brookfield", "Banglore"));
	}
	
	//Header versioning or custom versioning - Microsoft
	@GetMapping(value = "/address/header", headers = "X-API-VERSION=1")
	public Address1 getAddressHeaderV1() {
		return new Address1("Brookfield, Banglore");
	}
	
	@GetMapping(value = "/address/header", headers = "X-API-VERSION=2")
	public Address2 getAddresHeaderV2() {
		return new Address2(new Address("Brookfield", "Banglore"));
	}
	
	//Produces versioning or contente negotiation or accept header versioning or media type versoining - Github
	@GetMapping(value = "/address/produces", produces = "application/vnd.company.app-v1+json")
	public Address1 getAddressProducesV1() {
		return new Address1("Brookfield, Banglore");
	}
	
	@GetMapping(value = "/address/produces", produces = "application/vnd.company.app-v2+json")
	public Address2 getAddresProducesV2() {
		return new Address2(new Address("Brookfield", "Banglore"));
	}
}
