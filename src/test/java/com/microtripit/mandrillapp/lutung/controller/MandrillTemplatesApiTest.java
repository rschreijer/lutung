/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTemplate;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * @author rschreijer
 * @since Mar 22, 2013
 */
public final class MandrillTemplatesApiTest extends MandrillTestCase {

	@Test(expected=MandrillApiError.class)
	public final void testAddWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().add(null,
				"<html><body><h1>Hello World!</h1></body></html>", null);
		Assert.fail();
	}

	@Test
	public final void testAdd() throws IOException, MandrillApiError {

		String templateName = "lutung_templatename_unit_test_add1_" + System.currentTimeMillis();

		final MandrillTemplate t = mandrillApi.templates().add(templateName,
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test
	public final void testAddWithLabels() throws IOException, MandrillApiError {

		String templateName = "lutung_templatename_unit_test_add2_" + System.currentTimeMillis();
		String label1 = "1" + System.currentTimeMillis();
		String label2 = "2" + System.currentTimeMillis();
		String[] labels = {label1, label2};

		final MandrillTemplate t = mandrillApi.templates().add(templateName,
				"<html><body><h1>Hello World!</h1></body></html>", false, labels);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertTrue(t.getLabels().length == 2);
		Assert.assertTrue(t.getLabels()[0].equals(label1));
		Assert.assertTrue(t.getLabels()[1].equals(label2));
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test
	public final void testList() throws IOException, MandrillApiError {

		final MandrillTemplate[] startinTemplates = mandrillApi.templates().list();

		String templateName1 = "lutung_templatename_unit_test_list1_1_" + System.currentTimeMillis();
		String templateName2 = "lutung_templatename_unit_test_list1_2_" + System.currentTimeMillis();

		MandrillTemplate t1 = mandrillApi.templates().add(templateName1,
				"<html><body><h1>Hello World!</h1></body></html>", false);
		MandrillTemplate t2 = mandrillApi.templates().add(templateName2,
				"<html><body><h1>Hello World!</h1></body></html>", false);

		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		Assert.assertTrue(templates.length == (startinTemplates.length + 2));
		t1 = mandrillApi.templates().delete(t1.getName());
		Assert.assertNotNull(t1);
		t2 = mandrillApi.templates().delete(t2.getName());
		Assert.assertNotNull(t2);
	}

	@Test
	public final void testListFilterByLabel() throws IOException, MandrillApiError {

		String label1 = "1" + System.currentTimeMillis();
		String label2 = "2" + System.currentTimeMillis();
		String[] labels1 = {label1};
		String[] labels2 = {label2};

		String templateName1 = "lutung_templatename_unit_test_list2_1_" + System.currentTimeMillis();
		String templateName2 = "lutung_templatename_unit_test_list2_2_" + System.currentTimeMillis();

		MandrillTemplate t1 = mandrillApi.templates().add(templateName1,
				"<html><body><h1>Hello World!</h1></body></html>", false, labels1);
		MandrillTemplate t2 = mandrillApi.templates().add(templateName2,
				"<html><body><h1>Hello World!</h1></body></html>", false, labels2);

		final MandrillTemplate[] templatesFound1 = mandrillApi.templates().list(label1);
		Assert.assertNotNull(templatesFound1);
		Assert.assertTrue(templatesFound1.length == 1);

		final MandrillTemplate[] templatesFound2 = mandrillApi.templates().list(label2);
		Assert.assertNotNull(templatesFound2);
		Assert.assertTrue(templatesFound2.length == 1);

		t1 = mandrillApi.templates().delete(t1.getName());
		Assert.assertNotNull(t1);
		t2 = mandrillApi.templates().delete(t2.getName());
		Assert.assertNotNull(t2);
	}

	@Test(expected=MandrillApiError.class)
	public final void testInfoWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().info(null);
		Assert.fail();
	}

	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTemplate t =
					mandrillApi.templates().info(templates[0].getName());
			Assert.assertNotNull(t);
			Assert.assertNotNull(t.getName());
			Assert.assertNotNull(t.getCode());
			Assert.assertNotNull(t.getCreatedAt());
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().update(null,
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.fail();
	}

	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutCode() throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_update1_" + System.currentTimeMillis();
		mandrillApi.templates().update(templateName, null, false);
		Assert.fail();
	}

	@Test
	public final void testUpdate() throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_update2_" + System.currentTimeMillis();
		MandrillTemplate t = mandrillApi.templates().add(templateName,
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.assertNotNull(t);
		final String updatedCode = "<html><body><h1>Hello World! UPDATED</h1></body></html>";
		t = mandrillApi.templates().update(t.getName(), updatedCode, false);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertEquals(updatedCode, t.getCode());
		Assert.assertNotNull(t.getCreatedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test
	public final void testUpdateIncludingLabels() throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_update3_" + System.currentTimeMillis();
		String label1 = "1" + System.currentTimeMillis();
		String label2 = "2" + System.currentTimeMillis();
		String[] labels = {label1, label2};
		MandrillTemplate t = mandrillApi.templates().add(templateName,
				"<html><body><h1>Hello World!</h1></body></html>", false, labels);
		Assert.assertNotNull(t);
		final String updatedCode = "<html><body><h1>Hello World! UPDATED</h1></body></html>";
		String label3 = "3" + System.currentTimeMillis();
		labels[1] = label3;
		t = mandrillApi.templates().update(t.getName(), updatedCode, false, labels);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertEquals(updatedCode, t.getCode());
		Assert.assertNotNull(t.getCreatedAt());
		Assert.assertTrue(t.getLabels().length == 2);
		Assert.assertTrue(t.getLabels()[0].equals(label1));
		Assert.assertTrue(t.getLabels()[1].equals(label3));
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test(expected=MandrillApiError.class)
	public final void testPublishWithoutName()
			throws IOException, MandrillApiError {

		mandrillApi.templates().publish(null);
		Assert.fail();
	}

	@Test
	public final void testPublish() throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_publish_" + System.currentTimeMillis();
		MandrillTemplate t = mandrillApi.templates().add(
				templateName,
				"<html><body><h1>Hello World!</h1></body></html>",
				false);
		Assert.assertNotNull(t);
		Assert.assertNull(t.getPublishedAt());
		t = mandrillApi.templates().publish(t.getName());
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getPublishedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}

	@Test(expected=MandrillApiError.class)
	public final void testDeleteWithoutName()
			throws IOException, MandrillApiError {

		mandrillApi.templates().delete(null);
		Assert.fail();
	}

	@Test
	public final void testDelete() throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_delete_" + System.currentTimeMillis();
		MandrillTemplate t = mandrillApi.templates().add(
				templateName,
				"<html><body><h1>Hello World!</h1></body></html>",
				false);
		Assert.assertNotNull(t);
		t = mandrillApi.templates().delete(t.getName());
		Assert.assertNotNull(t);
	}

	@Test(expected=MandrillApiError.class)
	public final void testTimeSeriesWithoutName()
			throws IOException, MandrillApiError {

		mandrillApi.templates().timeSeries(null);
		Assert.fail();
	}

	@Test
	public final void testTimeSeries()
			throws IOException, MandrillApiError {

		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTimeSeries[] series =
					mandrillApi.templates().timeSeries(templates[0].getName());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries t : series) {
				Assert.assertNotNull(t.getTime());
			}
		}
	}

	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutName()
			throws IOException, MandrillApiError {

		mandrillApi.templates().render(
				null,
				new HashMap<String,String>(),
				null);
		Assert.fail();

	}

	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutContent()
			throws IOException, MandrillApiError {
		String templateName = "lutung_templatename_unit_test_render1_" + System.currentTimeMillis();
		mandrillApi.templates().render(templateName, null, null);
		Assert.fail();

	}

	@Test
	public final void testRender() throws IOException, MandrillApiError {
		final String code = "<html><body><h1>Hello *|MERGE1|*!</h1></body></html>";
		String templateName = "lutung_templatename_unit_test_render2_" + System.currentTimeMillis();
		MandrillTemplate t = mandrillApi.templates().add(templateName,
				code, false);
		Assert.assertNotNull(t);
		final HashMap<String, String> content = new HashMap<String,String>();
		content.put("editable", "<div>foo *|MERGE2|*</div>");
		final HashMap<String, String> mergeVars = new HashMap<String, String>();
		mergeVars.put("merge1", "Lutung");
		mergeVars.put("merge2", "bar");
		final String rendered = mandrillApi.templates().render(
				t.getName(), content, mergeVars);
		Assert.assertNotNull(rendered);
		Assert.assertEquals(code.replace("*|MERGE1|*", "Lutung"), rendered);
		t = mandrillApi.templates().delete(t.getName());
		Assert.assertNotNull(t);
	}

}
