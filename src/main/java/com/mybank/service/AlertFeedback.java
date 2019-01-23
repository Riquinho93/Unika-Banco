package com.mybank.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class AlertFeedback extends FeedbackPanel{

	private static final long serialVersionUID = 1L;

	/*private final MessageListView messageListView;*/
/*	WebMarkupContainer messagesContainer = new WebMarkupContainer("feedbackul") {
		private static final long serialVersionUID = 1L;

		@Override
		protected void onConfigure() {
			super.onConfigure();
			setVisible(anyMessage());
		}
	};*/

	public AlertFeedback(String id) {
		super(id);
		
		/*add(messagesContainer);
		messageListView = new MessageListView("messagens");
		messagesContainer.add(messageListView);*/

		/*if (filter != null) {
			setFilter(filter);
		}*/

	}

/*	public final boolean anyErrorMessage() {
		return anyMessage(FeedbackMessage.ERROR);
	}
	
	public final boolean anySucessMessage() {
		return anyMessage(FeedbackMessage.SUCCESS);
	}

	public final boolean anyMessage() {
		return anyMessage(FeedbackMessage.UNDEFINED);
	}

	public final boolean anyMessage(int level) {
		List<FeedbackMessage> msgs = getCurrentMessages();

		for (FeedbackMessage msg : msgs) {
			if (msg.isLevel(level)) {
				return true;
			}
		}

		return false;
	}

	public final FeedbackMessagesModel getFeedbackMessagesModel() {
		return (FeedbackMessagesModel) messageListView.getDefaultModel();
	}

	public final IFeedbackMessageFilter getFilter() {
		return getFeedbackMessagesModel().getFilter();
	}

	public final Validacao setFilter(IFeedbackMessageFilter filter) {
		getFeedbackMessagesModel().setFilter(filter);
		return this;
	}

	public final Comparator<FeedbackMessage> getSortingComparator() {
		return getFeedbackMessagesModel().getSortingComparator();
	}

	public final Validacao setSortingComparator(Comparator<FeedbackMessage> sortingComparator) {
		getFeedbackMessagesModel().setSortingComparator(sortingComparator);
		return this;
	}

	@Override
	public boolean isVersioned() {
		return false;
	}

	public final Validacao setMaxMessages(int maxMessages) {
		messageListView.setViewSize(maxMessages);
		return this;
	}
	protected final List<FeedbackMessage> getCurrentMessages() {
		final List<FeedbackMessage> messages = messageListView.getModelObject();
		return Collections.unmodifiableList(messages);
	}*/

	@Override
	protected String getCSSClass(final FeedbackMessage message) {
		String css = "feedback";
		if (message.getLevel() == FeedbackMessage.ERROR || message.getLevel() == FeedbackMessage.FATAL) {
			css = "alert alert-danger alertMessage";
		}
		if (message.getLevel() == FeedbackMessage.SUCCESS) {
			css = "alert alert-success alertMessage";
		}
		if (message.getLevel() == FeedbackMessage.WARNING) {
			css = "feedback warn";
		}

		return css;
	}
	
	


	/*protected FeedbackMessagesModel newFeedbackMessagesModel() {
		return new FeedbackMessagesModel(this);
	}

	protected Component newMessageDisplayComponent(String id, FeedbackMessage message) {
		Serializable serializable = message.getMessage();
		Label label = new Label(id, (serializable == null) ? "" : serializable.toString());
		label.setEscapeModelStrings(Validacao.this.getEscapeModelStrings());
		// label.add(new AttributeModifier("class",getCSSClass(message)));
		return label;
	}

	private final class MessageListView extends ListView<FeedbackMessage> {
		private static final long serialVersionUID = 1L;

		public MessageListView(final String id) {
			super(id);
			setDefaultModel(newFeedbackMessagesModel());
		}

		@Override
		protected IModel<FeedbackMessage> getListItemModel(final IModel<? extends List<FeedbackMessage>> listViewModel,
				final int index) {
			return new AbstractReadOnlyModel<FeedbackMessage>() {
				private static final long serialVersionUID = 1L;

				@Override
				public FeedbackMessage getObject() {
					if (index >= listViewModel.getObject().size()) {
						return null;
					} else {
						return listViewModel.getObject().get(index);
					}
				}
			};
		}

		@Override
		protected void populateItem(final ListItem<FeedbackMessage> listItem) {
			final FeedbackMessage message = listItem.getModelObject();
			message.markRendered();
			final Component label = newMessageDisplayComponent("message", message);
			final AttributeModifier levelModifier = AttributeModifier.replace("class", getCSSClass(message));
			// label.add(levelModifier);
			listItem.add(levelModifier);
			listItem.add(label);
			messagesContainer.add(levelModifier);

		}

	}*/

}
