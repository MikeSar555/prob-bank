package com.example.probbank.domain;

import java.util.Collection;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.core.GrantedAuthority;

public class UserTest {
	@Test
	public void getAuthorities() {
		User u = new User();
		Collection<? extends GrantedAuthority> expected = null;
		Collection<? extends GrantedAuthority> actual = u.getAuthorities();

		assertEquals(expected, actual);
	}

	@Test
	public void isAccountNonExpired() {
		User u = new User();
		boolean expected = true;
		boolean actual = u.isAccountNonExpired();

		assertEquals(expected, actual);
	}
}
