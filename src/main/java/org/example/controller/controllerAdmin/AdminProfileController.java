package org.example.controller.controllerAdmin;

import org.example.service.ProfileService;
import org.example.util.ScannerUtil;


public class AdminProfileController {

    ProfileService profileService;

    public AdminProfileController( ) {
        this.profileService = new ProfileService();
    }

    public void start() {

        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    get_profile_list();
                    break;
                case 2:
                    change_status();
                    break;
                case 0:
                    game = false;
                    break;
                default:
                    System.out.println("Nimabu Mazgi ");
            }

        }


    }

    private void change_status() {

    }

    private void get_profile_list() {
    profileService.get_profile_list();
    }

    private void menu() {
        System.out.println("*****  PROFILE MENU (ADMIN) *****");
        System.out.println("1.Profile list");
        System.out.println("2.Change status");
        System.out.println("0.Back to ADMIN MENU");
    }


}
