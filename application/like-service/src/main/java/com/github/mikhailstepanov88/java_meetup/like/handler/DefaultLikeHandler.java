package com.github.mikhailstepanov88.java_meetup.like.handler;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import com.github.mikhailstepanov88.java_meetup.like.service.LikeService;
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
public class DefaultLikeHandler implements LikeHandler {
    private final LikeService likeService;

    /**
     * Constructor.
     *
     * @param likeService service for working with likes.
     */
    public DefaultLikeHandler(@NonNull final LikeService likeService) {
        this.likeService = likeService;
    }

    /**
     * Handle create like operation.
     *
     * @param request request for handle.
     * @return response of create like operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleCreateLike(@NonNull final ServerRequest request) {
        return Mono.just(getIdeaIdFromRequest(request))
                .zipWith(request.bodyToMono(LikeDTO.class))
                .flatMap(ideaIdToLike -> likeService.createLike(
                        ideaIdToLike.getT1(),
                        ideaIdToLike.getT2()
                ))
                .flatMap(like -> ServerResponse.created(request.uriBuilder()
                        .path("/")
                        .path(like.getId())
                        .build()
                ).build())
                .switchIfEmpty(notFound().build())
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle read like by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read like by his identifier operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadLikeById(@NonNull final ServerRequest request) {
        return Mono.just(getIdeaIdFromRequest(request))
                .zipWith(Mono.just(getLikeIdFromRequest(request)))
                .flatMap(ideaIdToLikeId -> likeService.readLikeById(
                        ideaIdToLikeId.getT1(),
                        ideaIdToLikeId.getT2()
                ))
                .compose(like -> ok().body(like, LikeDTO.class))
                .switchIfEmpty(notFound().build())
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle read likes by idea identifier operation.
     *
     * @param request request for handle.
     * @return response of read likes by idea identifier operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadLikesByIdeaId(@NonNull final ServerRequest request) {
        return Mono.just(getIdeaIdFromRequest(request))
                .flatMapMany(likeService::readLikesByIdeaId)
                .collectList()
                .compose(likes -> ok().body(likes, new ParameterizedTypeReference<List<LikeDTO>>() {}))
                .onErrorResume(this::exceptionToResponse);
    }

    /**
     * Handle delete like operation.
     *
     * @param request request for handle.
     * @return response of delete like operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleDeleteLike(@NonNull final ServerRequest request) {
        return Mono.just(getIdeaIdFromRequest(request))
                .zipWith(Mono.just(getLikeIdFromRequest(request)))
                .flatMap(ideaIdToLikeId -> likeService.deleteLike(
                        ideaIdToLikeId.getT1(),
                        ideaIdToLikeId.getT2()
                ))
                .flatMap(result -> noContent().build())
                .switchIfEmpty(notFound().build())
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
     * Get identifier of like from request.
     *
     * @param request request for read.
     * @return identifier of like from request.
     */
    @NonNull
    private String getLikeIdFromRequest(@NonNull final ServerRequest request) {
        return Optional.of(request.pathVariable("like_id"))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Path variable with name \"like_id\" is not valid"));
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