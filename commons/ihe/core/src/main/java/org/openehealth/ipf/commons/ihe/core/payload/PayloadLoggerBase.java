/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.core.payload;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Base class for interceptors which store incoming and outgoing payload
 * into files with user-defined name patterns.
 * <p>
 * File name patterns can contain absolute and relative paths and must correspond to
 * <a href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/expressions.html">SpEL</a>
 * syntax, using square brackets for referencing placeholder parameters.
 * In the base version, the following parameters are supported
 * (this set can be extended in derived classes):
 * <ul>
 *     <li><tt>sequenceId</tt>&nbsp;&mdash; internally generated sequential ID
 *          as a 12-digit positive long int, zero-padded.</li>
 *     <li><tt>processId</tt>&nbsp;&mdash; process ID consisting from the OS process
 *          number and the host name, e.g. <tt>"12345-myhostname"</tt>.</li>
 *     <li><tt>date('format_spec')</tt>&nbsp;&mdash; current date and time, formatted
 *          using {@link java.text.SimpleDateFormat} according to the given specification.</li>
 * </ul>
 * <br>
 * Example of a file name pattern:<br>
 * <tt>C:/IPF-LOGS/[processId]/[date('yyyyMMdd-HH00')]/[sequenceId]-server-output.txt</tt>
 *
 * @author Dmytro Rud
 */
abstract public class PayloadLoggerBase<T extends PayloadLoggingContext> {
    private static final transient Logger LOG = LoggerFactory.getLogger(PayloadLoggerBase.class);

    private static final AtomicLong SEQUENCE_ID_GENERATOR = new AtomicLong(0L);

    public static final String SEQUENCE_ID_PROPERTY_NAME =
            PayloadLoggerBase.class.getName() + ".sequence.id";

    private static boolean globallyEnabled = true;

    private boolean locallyEnabled = true;

    private int errorCountLimit = -1;
    private int errorCount;

    private ExpressionResolver resolver;

    protected static Long getNextSequenceId() {
        return SEQUENCE_ID_GENERATOR.getAndIncrement();
    }

    protected void doLogPayload(T context, String charsetName, String... payloadPieces) {
        // check whether we can process
        if (! canProcess()) {
            return;
        }
        if ((errorCountLimit >= 0) && (errorCount >= errorCountLimit)) {
            LOG.warn("Error count limit has bean reached, reset the counter to enable further trials");
            return;
        }

        // compute file path
        String path = resolver.resolveExpression(context);

        // write payload pieces into the file
        Writer writer = null;
        try {
            FileOutputStream outputStream = FileUtils.openOutputStream(new File(path), true);
            writer = (charsetName != null) ?
                    new OutputStreamWriter(outputStream, charsetName) :
                    new OutputStreamWriter(outputStream);
            for (String payloadPiece : payloadPieces) {
                writer.write(payloadPiece);
            }
            errorCount = 0;
        } catch (IOException e) {
            ++errorCount;
            LOG.warn("Cannot write into " + path, e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }


    public boolean canProcess() {
        if (! (globallyEnabled && locallyEnabled)) {
            LOG.debug("File-based logging is disabled");
            return false;
        }
        return true;
    }


    /**
     * Resets count of occurred errors, can be used e.g. via JMX.
     */
    public void resetErrorCount() {
        errorCount = 0;
    }

    /**
     * @return <code>true</code> if this logging interceptor is enabled.
     * @see #isGloballyEnabled()
     */
    public boolean isLocallyEnabled() {
        return locallyEnabled;
    }

    /**
     * @param locallyEnabled
     *          <code>true</code> when this logging interceptor instance should be enabled.
     * @see #setGloballyEnabled(boolean)
     */
    public void setLocallyEnabled(boolean locallyEnabled) {
        this.locallyEnabled = locallyEnabled;
    }

    /**
     * @return <code>true</code> when logging interceptors are generally enabled.
     * @see #isLocallyEnabled()
     */
    public static boolean isGloballyEnabled() {
        return globallyEnabled;
    }

    /**
     * @param globallyEnabled
     *          <code>true</code> when logging interceptor should be generally enabled.
     * @see #setLocallyEnabled(boolean)
     */
    public static void setGloballyEnabled(boolean globallyEnabled) {
        PayloadLoggerBase.globallyEnabled = globallyEnabled;
    }

    /**
     * @return maximal allowed count of file creation errors, negative value means "no limit".
     */
    public int getErrorCountLimit() {
        return errorCountLimit;
    }

    /**
     * Configures maximal allowed count of file creation errors.
     * @param errorCountLimit
     *      maximal allowed count of file creation errors, negative value means "no limit".
     */
    public void setErrorCountLimit(int errorCountLimit) {
        this.errorCountLimit = errorCountLimit;
    }

    public ExpressionResolver getExpressionResolver() {
        return resolver;
    }

    public void setExpressionResolver(ExpressionResolver resolver) {
        this.resolver = resolver;
    }
}
