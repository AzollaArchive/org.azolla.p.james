package org.azolla.p.james.processer;

import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface Processer
{
    public Map<String, String> process(String s);

    public Map<String, String> process(List<String> sList);

    public Processer new0();

    public String getProcesserName();
}
