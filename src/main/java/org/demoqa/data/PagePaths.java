package org.demoqa.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagePaths extends PropertiesManager {

    public static final String BASE_DEMO_QA_UI_URL = properties.getProperty("demo_qa_base_url");

    public static final String TEXT_BOX_PAGE_URL = properties.getProperty("demo_qa_text_box_page_url");

    public static final String CHECKBOX_BOX_PAGE_URL = properties.getProperty("demo_qa_check_box_page_url");
}

