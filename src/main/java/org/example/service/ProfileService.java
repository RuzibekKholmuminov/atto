package org.example.service;

import org.example.controller.controllerAdmin.AdminController;
import org.example.controllerUser.UserController;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.ProfileRepository;
import org.example.util.MD5;

import java.util.List;

public class ProfileService {
    ProfileRepository profileRepository;

    public ProfileService() {
        this.profileRepository = new ProfileRepository();
    }

    public void login(String phone, String password) {
        Profile profile = profileRepository.searchByPhone(phone);

        if (profile == null) {
            System.out.println("Phone xato!");
            return;
        }

        if (!profile.getPassword().equals(password)) {
            System.out.println("Parol xato");
            return;
        }

        if (profile.getStatus().equals(GeneralStatus.BLOCK)) {
            System.out.println("Bu login blocklangan");
            return;
        }

        if (profile.getRole().equals(ProfileRole.ADMIN)) {
            AdminController adminController = new AdminController();
            adminController.start();

        } else if (profile.getRole().equals(ProfileRole.USER)) {

            UserController userController = new UserController();
            userController.start(profile);
        }


    }


    public void addProfile(Profile profile) {
        Profile profile1 = profileRepository.searchByPhone(profile.getPhone());

        if (profile1 != null) {
            System.out.println("Phone already exists");
            return;
        }

        int i = profileRepository.addProfileToDb(profile);

        if (i != 0) {
            System.out.println("Successfully");
            return;
        }

        System.out.println("Failed");

    }


    public void get_profile_list() {
        List<Profile> profile_list_fromDb = profileRepository.get_profile_list_fromDb();

        for (Profile profile : profile_list_fromDb) {
            System.out.println(profile);
        }

    }
}
