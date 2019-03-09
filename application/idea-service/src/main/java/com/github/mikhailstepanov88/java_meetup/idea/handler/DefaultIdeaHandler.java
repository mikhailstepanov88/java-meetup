package com.github.mikhailstepanov88.java_meetup.idea.handler;

import com.github.mikhailstepanov88.java_meetup.idea.data.dto.IdeaDTO;
import com.github.mikhailstepanov88.java_meetup.idea.service.IdeaService;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Primary
@Component
public class DefaultIdeaHandler implements IdeaHandler {
    private final IdeaService ideaService;

    /**
     * Constructor.
     *
     * @param ideaService service for working with ideas.
     */
    public DefaultIdeaHandler(@NonNull final IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    /**
     * Handle create idea operation.
     *
     * @param request request for handle.
     * @return response of create idea operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleCreateIdea(@NonNull final ServerRequest request) {
        return request.bodyToMono(IdeaDTO.class)
                .flatMap(ideaService::createIdea)
                .flatMap(idea -> created(request.uriBuilder()
                        .path("/")
                        .path(idea.getId())
                        .build()
                ).build())
                .switchIfEmpty(notFound().build())
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle read idea by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read idea by his identifier operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeaById(@NonNull final ServerRequest request) {
        return Mono.just(getIdeaIdFromRequest(request))
                .flatMap(ideaService::readIdeaById)
                .compose(idea -> ok().body(idea, IdeaDTO.class))
                .switchIfEmpty(notFound().build())
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle read ideas by pattern operation.
     *
     * @param request request for handle.
     * @return response of read ideas by pattern operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeasByPattern(@NonNull final ServerRequest request) {
        return Mono.just(getPatternFromRequest(request))
                .flatMapMany(ideaService::readIdeasByPattern)
                .collectList()
                .compose(ideas -> ok().body(ideas, new ParameterizedTypeReference<List<IdeaDTO>>() {}))
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle read ideas operation.
     *
     * @param request request for handle.
     * @return response of read ideas operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeas(@NonNull final ServerRequest request) {
        return ideaService.readIdeas()
                .collectList()
                .compose(ideas -> ok().body(ideas, new ParameterizedTypeReference<List<IdeaDTO>>() {}))
                .onErrorResume(this::exceptionToResponse);
    }

    //<editor-fold desc="private additional methods">
    /**
     * Get identifier of idea from request.
     *
     * @param request request for read.
     * @return identifier of idea from request.
     */
    @NonNull
    private String getIdeaIdFromRequest(@NonNull final ServerRequest request) {
        return Optional.of(request.pathVariable("idea_id"))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Path variable with name \"idea_id\" is not valid"));
    }

    /**
     * Get pattern from request.
     *
     * @param request request for read.
     * @return pattern from request.
     */
    @NonNull
    private String getPatternFromRequest(@NonNull final ServerRequest request) {
        return request.queryParam("pattern")
                .orElseThrow(() -> new IllegalArgumentException(
                        "Query parameter with name \"pattern\" is not valid"));
    }

    /**
     * Convert exception to server response.
     *
     * @param ex exception for convert.
     * @return converted server response.
     */
    @NonNull
    private Mono<ServerResponse> exceptionToResponse(@NonNull Throwable ex) {
        if (ex instanceof IllegalArgumentException)
            return badRequest().body(Mono.just(ex.getMessage()), String.class);
        else
            return status(501).body(Mono.just("Something goes wrong"), String.class);
    }
    //</editor-fold>
}