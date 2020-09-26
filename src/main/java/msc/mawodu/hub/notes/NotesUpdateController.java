package msc.mawodu.hub.notes;


import msc.mawodu.hub.Routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
public class NotesUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(NotesUpdateController.class);

    @PostMapping(value= Routes.PIPELINE_NOTES_UPDATE)
    public String updateNotes() {
        return "OK";
    }

    @PostMapping("/notes")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody NotesContent notesContent, Errors requestErrors) {
        logger.info("Received notes update request.");

        if (requestErrors.hasErrors()) {
            return errorResponse(requestErrors);
        }

        String updatedNotes = notesContent + " - UPDATED";
        return ResponseEntity.ok(NotesUpdateResponse.correct(updatedNotes));

    }

    private ResponseEntity<?> errorResponse(Errors errors) {
        return ResponseEntity
                .badRequest()
                .body(NotesUpdateResponse
                        .withError(errors.getAllErrors()
                                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.joining(","))
                        )
                );
    }

}

