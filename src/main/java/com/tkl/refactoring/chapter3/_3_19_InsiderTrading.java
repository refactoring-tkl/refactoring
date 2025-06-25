//package com.tkl.refactoring.chapter3;
//
//public class _3_19_InsiderTrading {
//
//	class TicketTemplateService {
//		enum TemplateType {
//			TEMPLATE {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, TicketTemplateService ticketTemplateService) {
//					return PRODUCT.isNewTemplate(ticketTemplate, ticketTemplateService);
//				}
//			}, PRODUCT {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, TicketTemplateService ticketTemplateService) {
//					LOG.debug("here is not RECEIPT");
//					return ticketTemplateService.checkNewTemplateName(ticketTemplate);
//				}
//			}, RECEIPT {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, TicketTemplateService ticketTemplateService) {
//					LOG.debug("here is RECEIPT");
//					return ticketTemplateService.checkReceiptTemplateId(ticketTemplate);
//				}
//			};
//
//			abstract boolean isNewTemplate(TicketTemplate ticketTemplate, TicketTemplateService ticketTemplateService);
//		}
//
//		private boolean checkReceiptTemplateId(TicketTemplate ticketTemplate) {
//		}
//
//		private boolean checkNewTemplateName(TicketTemplate ticketTemplate) {
//			return false;
//		}
//
//
//		/**
//		 * ===== After Refactoring =====
//		 */
//		enum TemplateType_after {
//			TEMPLATE {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, boolean isNewProductTemplate, boolean isNewReceiptTemplate) {
//					return PRODUCT.isNewTemplate(ticketTemplate, isNewProductTemplate, isNewReceiptTemplate);
//				}
//			},
//			PRODUCT {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, boolean isNewProductTemplate, boolean isNewReceiptTemplate) {
//					return isNewProductTemplate;
//				}
//			},
//			RECEIPT {
//				@Override
//				boolean isNewTemplate(TicketTemplate ticketTemplate, boolean isNewProductTemplate, boolean isNewReceiptTemplate) {
//					return isNewReceiptTemplate;
//				}
//			};
//
//			abstract boolean isNewTemplate(TicketTemplate ticketTemplate, boolean isNewProductTemplate, boolean isNewReceiptTemplate);
//		}
//		public void addOrModify(TicketTemplate ticketTemplate) {
//			boolean isNewProductTemplate = checkNewTemplateName(ticketTemplate);
//			boolean isNewReceiptTemplate = checkReceiptTemplateId(ticketTemplate);
//
//			TemplateType type = TemplateType.valueOf(ticketTemplate.getTemplateType().toUpperCase());
//			if (type.isNewTemplate(ticketTemplate, isNewProductTemplate, isNewReceiptTemplate)) {
//				insertTicketTemplate(ticketTemplate);
//			} else {
//				modifyTicketTemplate(ticketTemplate);
//			}
//		}
//
//	}
//
//
//}
//class TicketTemplate {
//}
