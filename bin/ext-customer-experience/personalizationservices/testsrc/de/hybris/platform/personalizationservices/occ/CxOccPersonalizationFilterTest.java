package de.hybris.platform.personalizationservices.occ;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.personalizationservices.configuration.CxConfigurationService;
import de.hybris.platform.personalizationservices.model.config.CxConfigModel;
import de.hybris.platform.personalizationservices.service.CxRecalculationService;
import de.hybris.platform.servicelayer.session.impl.DefaultSessionTokenService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Validator;


@UnitTest
public class CxOccPersonalizationFilterTest
{
	@Mock
	private DefaultSessionTokenService tokenService;

	@Mock
	private CxConfigurationService cxConfigurationService;

	@Mock
	private CxRecalculationService cxRecalculationService;

	@Mock
	private CxOccAttributesStrategy cxOccAttributesStrategy;

	@Mock
	private HttpServletRequest httpServletRequest;

	@Mock
	private HttpServletResponse httpServletResponse;

	@Mock
	private FilterChain filterChain;

	private CxOccPersonalizationFilter filter;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		filter = new CxOccPersonalizationFilter();
		filter.setCxConfigurationService(cxConfigurationService);
		filter.setTokenService(tokenService);
		filter.setCxRecalculationService(cxRecalculationService);
		filter.setCxOccAttributesStrategy(cxOccAttributesStrategy);

		final List<CxOccInterceptor> interceptors = Collections.emptyList();
		filter.setInterceptors(Optional.of(interceptors));

		final CxConfigModel configModel = new CxConfigModel();
		configModel.setOccPersonalizationEnabled(true);

		doReturn(Optional.of(configModel)).when(cxConfigurationService).getConfiguration();

		when(cxOccAttributesStrategy.readPersonalizationId(any())).thenReturn(Optional.of(""));
	}

	@Test
	public void doFilterInternalShouldCallDoFilterWhenExceptionHappensInHandlePersonalization()
			throws ServletException, IOException
	{
		//given

		doThrow(new RuntimeException()).when(cxConfigurationService).getConfiguration();

		//when

		filter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

		//then

		verify(filterChain, Mockito.times(1)).doFilter(any(), any());
	}

	@Test
	public void doFilterInternalShouldCallDoFilterWithoutExceptionInHandlePersonalization() throws ServletException, IOException
	{
		//when

		filter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

		//then

		verify(filterChain, Mockito.times(1)).doFilter(any(), any());
	}
}
