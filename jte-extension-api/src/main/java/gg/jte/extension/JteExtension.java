package gg.jte.extension;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A jte extension must implement this interface.
 */
public interface JteExtension {
    /**
     *
     * @return descriptive name of the extension
     */
    String name();

    /**
     * Perform the file generation.
     * @param config access to general jte configuration
     * @param templateDescriptions data about each template jte found.
     * @return the paths to each file generated by this extension
     */
    Collection<Path> generate(JteConfig config, Set<TemplateDescription> templateDescriptions);

    /**
     * init will be called after the extension instance is constructed. The map can be used to pass additional
     * configuration properties from a build script.
     * @param value Map of property key to configured value
     * @return self or a replacement
     */
    default JteExtension init(Map<String, String> value) {
        return this;
    }
}