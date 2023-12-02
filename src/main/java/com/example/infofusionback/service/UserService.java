package com.example.infofusionback.service;

import java.util.Optional;

import com.example.infofusionback.entity.BO.UserBO;

public interface UserService {
	/**
	 * Retrouver un utilisateur par email
	 *
	 * @param email Email de l'utilisateur recherché
	 * @return Utilisateur
	 */
	public Optional<UserBO> findUserByEmail(String email);

	/**
	 * Création d'un nouvel utilisateur
	 *
	 * @param userBO Nouvel utilisateur
	 * @return Nouvel Utilisateur
	 */
	public UserBO saveUser(UserBO userBO);
	//UserBO getUserById(Long id);
	//List<UserBO> getAllUsers();
	//void deleteUser(Long id);
}