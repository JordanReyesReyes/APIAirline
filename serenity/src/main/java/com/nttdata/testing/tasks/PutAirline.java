package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutAirline implements Task {
    private final String endpoint;
    private final String requestBody;

    public PutAirline(String endpoint, String requestBody) {
        this.endpoint = endpoint;
        this.requestBody = requestBody;
    }

    public static Performable withData(String endpoint, String requestBody) {
        return instrumented(PutAirline.class, endpoint, requestBody);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(endpoint)
                        .with(request -> request.contentType(ContentType.JSON).body(requestBody).log().all())
        );
    }
}

