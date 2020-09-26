package msc.mawodu.hub.notes;


import msc.mawodu.hub.Routes;
import msc.mawodu.hub.mocks.MockInMemoryNotesDatabase;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
public class NotesUpdateController {

    @Autowired
    MockInMemoryNotesDatabase notesDatabase;

    private static final Logger logger = LoggerFactory.getLogger(NotesUpdateController.class);

    @PostMapping(value= Routes.PIPELINE_NOTES_UPDATE)
    public ResponseEntity<?> getSearchResultViaAjax(@PathVariable final String pipelineId, @Valid @RequestBody NotesContent notesContent, Errors requestErrors) {
        logger.info("Received notes update request.");

        if (requestErrors.hasErrors()) {
            return errorResponse(requestErrors);
        }

        String sanitisedNotes = Jsoup.parse(notesContent.getNotes()).text();

        boolean pipelineNotesupdateStatus = notesDatabase.updatePipelineNotes(pipelineId, sanitisedNotes);

        return ResponseEntity.ok(NotesUpdateResponse.correct(String.valueOf(pipelineNotesupdateStatus)));

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

