package msc.mawodu.hub;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesUpdateController {

    @PostMapping(value=Routes.PIPELINE_NOTES_UPDATE)
    public String updateNotes() {
        return "OK";
    }

}
