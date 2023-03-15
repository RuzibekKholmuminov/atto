package org.example.service;

import org.example.dto.Terminal;
import org.example.enums.GeneralStatus;
import org.example.repository.TerminalRepository;

import java.util.List;

public class TerminalService {
    TerminalRepository terminalRepository;

    public TerminalService() {
        this.terminalRepository = new TerminalRepository();
    }

    public void create(Terminal terminal) {
        List<Terminal> terminal1 = terminalRepository.getTerminal(terminal.getCode());
        for (Terminal terminal2 : terminal1) {
            if (terminal2 != null) {
                System.out.println("Bunday kodli terminal mavjud");
                return;
            }
        }
        int i = terminalRepository.addTerminalToDb(terminal);
        if (i != 0) {
            System.out.println("Terminal Created");
        } else {
            System.out.println("Failed");
        }


    }


    public void get_terminal_list() {
        List<Terminal> terminal_list_fromDb = terminalRepository.get_terminal_list_fromDb();
        for (Terminal terminal : terminal_list_fromDb) {
            System.out.println(terminal);
        }

    }

    public void updateTerminal_address(String code, String address) {

        int i = terminalRepository.updateTerminal_address_fromDB(code, address);
        if (i == 0) {
            System.out.println("Failed");
        } else {
            System.out.println("Successfully");
        }
    }

    public void changeTerminal_status(String code) {
        List<Terminal> terminal = terminalRepository.getTerminal(code);
        String status = "";
        int i = 0;
        for (Terminal terminal1 : terminal) {
            if (terminal1 == null) {
                System.out.println("Bunday terminal mavjud emas");
                return;
            }
            if (terminal1.getStatus().equals(GeneralStatus.BLOCK.toString())) {
                status = GeneralStatus.ACTIVE.toString();
                i = terminalRepository.changeTerminal_ACTIVE_status_fromDB(code, status);
            }else if (terminal1.getStatus().equals(GeneralStatus.ACTIVE.toString())){
                status = GeneralStatus.BLOCK.toString();
                i = terminalRepository.changeTerminal_BLOCK_status_fromDB(code, status);
            }
        }
        if (i == 0) {
            System.out.println("Failed");
        } else {
            System.out.println("Terminal status changed to " + status + " successfully");
        }
    }

    public void deleteTerminal(String code) {
        int i = terminalRepository.deleteTerminal_fromDb(code);
        if (i==0){
            System.out.println("Failed");
        }
        else {
            System.out.println("Deleted");
        }
    }
}
