/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

/**
 * @author rschreijer
 * @since Mar 18, 2013
 */
public final class MandrillHelperClasses {
	
	
	public static class EmailClass {
		private String email;
		public final String getEmail() {
			return email;
		}
	}
	/**
	 * @author rschreijer
	 * @since Sep 27, 2013
	 */
	public static class MandrillRejectsAdded extends EmailClass {
		/*
		 * This class is only used in one call.
		 */
		private Boolean added;
		public final Boolean getAdded() {
			return added;
		}
	}
	
	/**
	 * @author rschreijer
	 * @since Mar 18, 2013
	 */
	public static class MandrillRejectsDeleted extends EmailClass {
		/*
		 * This class is only used in one call.
		 */
		private Boolean deleted;
		public final Boolean getDeleted() {
			return deleted;
		}
	}
	
	/**
	 * <p>Simple Wrapper class that is a container for a 
	 * <code>name,content</code> pair, as String objects.</p>
	 * @author rschreijer
	 * @since Mar 18, 2013
	 */
	public static final class MandrillContentWrapper {
		private String name, content;

		/**
		 * @return the name
		 */
		public final String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public final void setName(final String name) {
			this.name = name;
		}

		/**
		 * @return the content
		 */
		public final String getContent() {
			return content;
		}

		/**
		 * @param content the content to set
		 */
		public final void setContent(final String content) {
			this.content = content;
		}
		
		public static final MandrillContentWrapper create(
				final String name, final String content) {
			
			final MandrillContentWrapper w = new MandrillContentWrapper();
			w.setName(name);
			w.setContent(content);
			return w;
			
		}

	}
	
	/**
	 * <p>The result of rendering the given template with the 
	 * content and merge field values injected.</p>
	 * @author rschreijer
	 * @since Mar 18, 2013
	 */
	public static final class MandrillRenderTemplateResponse {
		private String html;
		
		/**
		 * @return The rendered HTML as a string.
		 */
		public final String getHtml() {
			return html;
		}
	}
	
}
