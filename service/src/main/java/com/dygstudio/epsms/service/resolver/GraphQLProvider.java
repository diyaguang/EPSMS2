package com.dygstudio.epsms.service.resolver;

import com.dygstudio.epsms.service.entity.Client;
import com.dygstudio.epsms.service.entity.User;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 〈功能概述〉
 *
 * @className: GraphQLProvider
 * @package: com.dygstudio.epsms.service.resolver
 * @author: diyaguang
 * @date: 2020/7/4 11:09
 */
@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:Client.graphql");
        GraphQLSchema graphQLSchema = createGraphQLSchema(file);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQLSchema createGraphQLSchema(File file) {
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(file);
        return schemaGenerator.makeExecutableSchema(typeRegistry, buildResolver());
    }

    public RuntimeWiring buildResolver() {
        return RuntimeWiring.newRuntimeWiring().type("ClientQuery", builder ->
                builder.dataFetcher("client", dataFetchingEnvironment -> {
                    String id = dataFetchingEnvironment.getArgument("id");
                    return new Client(id,"springboot+graphql",37);
                })
        ).build();
    }

    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }
}
