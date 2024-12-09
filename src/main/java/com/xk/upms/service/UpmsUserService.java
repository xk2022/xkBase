package com.xk.upms.service;

import java.util.List;

import com.xk.upms.model.bo.UpmsUserReq;
import com.xk.upms.model.bo.UpmsUserSaveReq;
import com.xk.upms.model.vo.UpmsUserResp;

/**
 * Service interface for managing UPMS users.
 * <p>
 * This interface provides methods to perform CRUD operations and support search
 * and retrieval of user entities in the system.
 * </p>
 *
 * <p>
 * Key Features:
 * <ul>
 * <li>Encapsulation of business logic for user management</li>
 * <li>Support for filtering and querying users via request objects</li>
 * <li>Entity-to-DTO mapping for consistent external API responses</li>
 * </ul>
 * </p>
 *
 * @author yuan
 * @version 1.2, Updated on 2024/12/06
 */
public interface UpmsUserService {

	/**
	 * Retrieves a list of all users or filters users based on the specified
	 * criteria.
	 *
	 * @param resources a {@link UpmsUserReq} object containing filter criteria.
	 * @return a list of {@link UpmsUserResp} representing matching users.
	 */
	List<UpmsUserResp> getList(UpmsUserReq resources);

	/**
	 * Retrieves the details of a specific user by their unique ID.
	 *
	 * @param id the unique identifier of the user.
	 * @return a {@link UpmsUserResp} object containing the user's details, or
	 *         {@code null} if no user is found with the specified ID.
	 */
	UpmsUserResp getOneById(Long id);

	/**
	 * Creates a new user in the system.
	 *
	 * @param resources a {@link UpmsUserReq} object containing the user's details.
	 * @return a {@link UpmsUserResp} object representing the newly created user.
	 */
	UpmsUserResp create(UpmsUserSaveReq resources);

	/**
	 * Updates the details of an existing user.
	 *
	 * @param id        the unique identifier of the user to update.
	 * @param resources a {@link UpmsUserReq} object containing the updated details.
	 * @return a {@link UpmsUserResp} object with the updated user details, or
	 *         {@code null} if the user does not exist.
	 */
	UpmsUserResp update(Long id, UpmsUserSaveReq resources);

	/**
	 * Deletes a user from the system by their ID.
	 *
	 * @param id the unique identifier of the user to delete.
	 * @return {@code true} if the user was successfully deleted; {@code false} if
	 *         the user does not exist or deletion failed.
	 */
	boolean deleteUser(Long id);

}
