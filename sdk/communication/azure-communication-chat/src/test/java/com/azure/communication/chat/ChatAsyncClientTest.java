// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.communication.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

import com.azure.communication.administration.CommunicationIdentityClient;
import com.azure.communication.administration.CommunicationUserToken;
import com.azure.communication.common.CommunicationUser;
import com.azure.communication.chat.implementation.ChatOptionsProvider;
import com.azure.communication.chat.models.*;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.logging.ClientLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Set the AZURE_TEST_MODE environment variable to either PLAYBACK or RECORD to determine if tests are playback or
 * live. By default, tests are run in playback mode.
 */
public class ChatAsyncClientTest extends ChatClientTestBase {

    private ClientLogger logger = new ClientLogger(ChatClientTest.class);

    private CommunicationIdentityClient communicationClient;
    private ChatAsyncClient client;

    private CommunicationUser firstThreadMember;
    private CommunicationUser secondThreadMember;

    @Override
    protected void beforeTest() {
        super.beforeTest();

        communicationClient = getCommunicationIdentityClientBuilder().buildClient();
        assertNotNull(communicationClient);

        firstThreadMember = communicationClient.createUser();
        secondThreadMember = communicationClient.createUser();

        List<String> scopes = new ArrayList<String>(Arrays.asList("chat"));
        CommunicationUserToken response = communicationClient.issueToken(firstThreadMember, scopes);

        client = getChatClientBuilder(response.getToken()).buildAsyncClient();
    }

    @Override
    protected void afterTest() {
        super.afterTest();
    }

    @Test
    public void canCreateThread() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());

        // Act & Assert
        StepVerifier.create(client.createChatThread(threadRequest))
            .assertNext(chatThreadClient -> {
                assertNotNull(chatThreadClient);
                assertNotNull(chatThreadClient.getChatThreadId());
            })
            .verifyComplete();
    }

    @Test
    public void canCreateThreadWithResponse() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());

        // Act & Assert
        StepVerifier.create(client.createChatThreadWithResponse(threadRequest))
            .assertNext(chatThreadClientResponse -> {
                ChatThreadAsyncClient chatThreadClient = chatThreadClientResponse.getValue();
                assertNotNull(chatThreadClient);
                assertNotNull(chatThreadClient.getChatThreadId()); 
            })
            .verifyComplete();
    }

    @Test
    public void canGetChatThreadClient() {
        // Arrange
        String threadId = "19:fe0a2f65a7834185b29164a7de57699c@thread.v2";

        // Act
        ChatThreadAsyncClient chatThreadClient = client.getChatThreadClient(threadId);

        // Assert
        assertNotNull(chatThreadClient);
        assertEquals(chatThreadClient.getChatThreadId(), threadId);
    }

    @Test
    public void canGetExistingChatThread() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());

        // Act & Assert
        AtomicReference<ChatThreadAsyncClient> chatThreadClientRef = new AtomicReference<>();
        StepVerifier.create(
            client.createChatThread(threadRequest)
                .flatMap(chatThreadClient -> {
                    chatThreadClientRef.set(chatThreadClient);
                    return client.getChatThread(chatThreadClient.getChatThreadId());
                }))
            .assertNext(chatThread -> {
                assertEquals(chatThreadClientRef.get().getChatThreadId(), chatThread.getId());
            })
            .verifyComplete();
    }

    @Test
    public void canGetExistingChatThreadWithResponse() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());

        // Act & Assert
        AtomicReference<ChatThreadAsyncClient> chatThreadClientRef = new AtomicReference<>();
        StepVerifier.create(
            client.createChatThread(threadRequest)
            .flatMap(chatThreadClient -> {
                chatThreadClientRef.set(chatThreadClient);
                return client.getChatThreadWithResponse(chatThreadClient.getChatThreadId());
            }))
            .assertNext(chatThreadResponse -> {
                ChatThread chatThread = chatThreadResponse.getValue();
                assertEquals(chatThreadClientRef.get().getChatThreadId(), chatThread.getId());
            })
            .verifyComplete();
    }

    @Test
    public void getNotFoundOnNonExistingChatThread() {
        // Act & Assert
        StepVerifier.create(client.getChatThread("19:020082a8df7b44dd8c722bea8fe7167f@thread.v2"))
            .expectErrorMatches(exception ->
                ((HttpResponseException) exception).getResponse().getStatusCode() == 404
            )
            .verify();
    }

    @Test
    public void getNotFoundOnNonExistingChatThreadWithResponse() {
        // Act & Assert
        StepVerifier.create(client.getChatThreadWithResponse("19:020082a8df7b44dd8c722bea8fe7167f@thread.v2"))
            .expectErrorMatches(exception ->
                ((HttpResponseException) exception).getResponse().getStatusCode() == 404
            )
            .verify();
    }

    @Test
    public void canDeleteChatThread() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());

        // Act & Assert
        AtomicReference<ChatThreadAsyncClient> chatThreadClientRef = new AtomicReference<>();
        StepVerifier.create(
            client.createChatThread(threadRequest)
                .flatMap(chatThreadClient -> {
                    chatThreadClientRef.set(chatThreadClient);
                    return client.deleteChatThread(chatThreadClient.getChatThreadId());
                })
            )
            .verifyComplete();
    }

    @Test
    public void canDeleteChatThreadWithResponse() {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());
        
        // Act & Assert
        AtomicReference<ChatThreadAsyncClient> chatThreadClientRef = new AtomicReference<>();
        StepVerifier.create(
            client.createChatThread(threadRequest)
                .flatMap(chatThreadClient -> {
                    chatThreadClientRef.set(chatThreadClient);
                    return client.deleteChatThreadWithResponse(chatThreadClient.getChatThreadId());
                })
            )
            .assertNext(deleteResponse -> {
                assertEquals(deleteResponse.getStatusCode(), 204);
            })
            .verifyComplete();
    }

    @Test
    public void canListChatThreads() throws InterruptedException {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());
        
        StepVerifier.create(
                client.createChatThread(threadRequest)
                    .concatWith(client.createChatThread(threadRequest)))
            .assertNext(chatThreadClient -> {
                // Act & Assert
                PagedIterable<ChatThreadInfo> threadsResponse = new PagedIterable<>(client.listChatThreads());

                // process the iterableByPage
                List<ChatThreadInfo> returnedThreads = new ArrayList<ChatThreadInfo>();
                threadsResponse.iterableByPage().forEach(resp -> {
                    assertEquals(resp.getStatusCode(), 200);
                    resp.getItems().forEach(item -> returnedThreads.add(item));
                });

                assertTrue(returnedThreads.size() == 2);
            });
    }

    @Test
    public void canListChatThreadsWithMaxPageSize() throws InterruptedException {
        // Arrange
        CreateChatThreadOptions threadRequest = ChatOptionsProvider.createThreadOptions(
            firstThreadMember.getId(), secondThreadMember.getId());
        
        ListChatThreadsOptions options = new ListChatThreadsOptions();
        options.setMaxPageSize(10);

        StepVerifier.create(
            client.createChatThread(threadRequest)
                .concatWith(client.createChatThread(threadRequest)))
            .assertNext(chatThreadClient -> {
                // Act & Assert
                PagedIterable<ChatThreadInfo> threadsResponse = new PagedIterable<>(client.listChatThreads(options));

                // process the iterableByPage
                List<ChatThreadInfo> returnedThreads = new ArrayList<ChatThreadInfo>();
                threadsResponse.iterableByPage().forEach(resp -> {
                    assertEquals(resp.getStatusCode(), 200);
                    resp.getItems().forEach(item -> returnedThreads.add(item));
                });

                assertTrue(returnedThreads.size() == 2);
            });
    }
}
