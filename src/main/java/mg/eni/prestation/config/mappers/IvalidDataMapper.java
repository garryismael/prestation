package mg.eni.prestation.config.mappers;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IvalidDataMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {

        final Map<String, String> errorResponse = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(o -> {
                    String queryParamPath = o.getPropertyPath().toString();
                    String queryParam = queryParamPath.contains(".")
                            ? queryParamPath.substring(queryParamPath.lastIndexOf(".") + 1)
                            : queryParamPath;
                    return queryParam;
                }, o -> o.getMessage()));

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
