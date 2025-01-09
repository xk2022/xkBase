package com.xk.upms.service;

import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.model.vo.UpmsUserResp;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

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
	 * @param example a {@link UpmsUser} object containing filter criteria.
	 * @return a list of {@link UpmsUser} representing matching users.
	 */
	List<UpmsUser> getList(Example<UpmsUser> example, Sort sort);

	/**
	 * Retrieves the details of a specific user by their unique ID.
	 *
	 * @param id the unique identifier of the user.
	 * @return a {@link UpmsUser} object containing the user's details, or
	 *         {@code null} if no user is found with the specified ID.
	 */
	UpmsUser getOneById(Long id);

	/**
	 * Creates a new user in the system.
	 *
	 * @param upmsUser a {@link UpmsUser} object containing the user's details.
	 * @return a {@link UpmsUserResp} object representing the newly created user.
	 */
	UpmsUser create(UpmsUser upmsUser);

	/**
	 * Updates the details of an existing user.
	 *
	 * @param upmsUser a {@link UpmsUser} object containing the updated details.
	 * @return a {@link UpmsUserResp} object with the updated user details, or
	 *         {@code null} if the user does not exist.
	 */
	UpmsUser update(UpmsUser upmsUser);

	/**
	 * Deletes a user from the system by their ID.
	 *
	 * @param upmsUser a {@link UpmsUser} object containing the delete details.
	 */
	void delete(UpmsUser upmsUser);

}
